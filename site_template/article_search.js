console.clear();
const articleList = [];

$.get(
	'https://api.github.com/repos/vuejs/vue/commits?per_page=20&sha=master',
	{},
	function(data) {
		data.forEach((row, index) => {
			console.log(row);

			const article = {
				id: index + 1,
				regDate: row.commit.author.date,
				writer: row.commit.author.name,
				title: row.commit.message,
				body: row.commit.message
			};

			articleList.push(article);
		});
	},
	'json'
);

const articleListBoxVue = new Vue({
	el: "#article-list-wrap",
	data: {
		articleList: articleList,
		searchKeyword: ''
	},
	methods: {
		searchKeywordInputed: _.debounce(function(e) {
			this.searchKeyword = e.target.value;
		}, 500)
	},
	computed: {
		filterKey: function() {
			return this.searchKeyword.toLowerCase();
		},
		filtered: function() {
			if (this.filterKey.length == 0) {
				return this.articleList;
			}

			return this.articleList.filter((row) => {
				const keys = ['title', 'writer', 'body', 'regDate'];

				const match = keys.some((key) => {
					if (row[key].toLowerCase().indexOf(this.filterKey) > -1) {
						return true;
					}
				});

				return match;
			});
		}
	}
});