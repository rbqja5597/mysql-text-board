package com.sbs.example.mysqlTextBoard.Util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format.format(time);
	}

	// 파일에 내용쓰기
	public static void writeFileContents(String filePath, int data) {
		writeFileContents(filePath, data + "");
	}

	// 첫 문자 소문자화
	public static String lcfirst(String str) {
		String newStr = "";
		newStr += str.charAt(0);
		newStr = newStr.toLowerCase();

		return newStr + str.substring(1);
	}

	// 파일이 존재하는지
	public static boolean isFileExists(String filePath) {
		File f = new File(filePath);
		if (f.isFile()) {
			return true;
		}

		return false;
	}

	// 파일내용 읽어오기
	public static String getFileContents(String filePath) {
		String rs = null;
		try {
			// 바이트 단위로 파일읽기
			FileInputStream fileStream = null; // 파일 스트림

			fileStream = new FileInputStream(filePath);// 파일 스트림 생성
			// 버퍼 선언
			byte[] readBuffer = new byte[fileStream.available()];
			while (fileStream.read(readBuffer) != -1) {
			}

			rs = new String(readBuffer);

			fileStream.close(); // 스트림 닫기
		} catch (Exception e) {
			e.getStackTrace();
		}

		return rs;
	}

	// 파일 쓰기
	public static void writeFileContents(String filePath, String contents) {
		BufferedOutputStream bs = null;
		try {
			bs = new BufferedOutputStream(new FileOutputStream(filePath));
			bs.write(contents.getBytes()); // Byte형으로만 넣을 수 있음
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				bs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void mkdirs(String path) {
		File dir = new File(path);
		
		if (dir.exists() == false) {
			dir.mkdirs();
		}
	}

	public static void writeFile(String path, String body) {
		File file = new File(path);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(body);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static boolean rmdir(String path) {
		return rmdir(new File(path));
	}
	

	public static boolean rmdir(File dirToBeDeleted) {
		File[] allContents = dirToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				rmdir(file);
			}
		}
		
		return dirToBeDeleted.delete();
	}

	public static boolean copy(String sourcePath, String destPath) {
		Path source = Paths.get(sourcePath);
		Path target = Paths.get(destPath);
		
		if (!Files.exists(target.getParent())) {
			try {
				Files.createDirectories(target.getParent());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return true;
		}
		
		return true;
	}

	public static void copyDir(String sourceDirectoryLocation, String destinationDirectoryLocation) {
		rmdir(destinationDirectoryLocation);

		try {
			Files.walk(Paths.get(sourceDirectoryLocation)).forEach(source -> {
				Path destination = Paths.get(destinationDirectoryLocation,
						source.toString().substring(sourceDirectoryLocation.length()));
				try {
					Files.copy(source, destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

