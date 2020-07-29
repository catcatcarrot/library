/** layuiAdmin.pro-v1.2.1 LPPL License By http://www.layui.com/admin/ */ ;
layui.define(["table", "form"], function(e) {
	var i = (layui.$, layui.admin),
		t = layui.view,
		l = layui.table,
		r = layui.form;
		
	 l.render({
		elem: "#LAY-book-manage",
		url: "/findAllBooks",
		method:"GET",
		cols: [
			[  {
					field: "id",
					width: 80,
					title: "序号",
					align: "center"
				},{
					field: "bookId",
					title: "书的id",
					hide:true,
					align: "center"
				}, {
					field: "bookName",
					width: 200,
					title: "书名",
					align: "center"
				},
				{
					field: "bookAuthor",
					width: 120,
					align: "center",
					title: "作者"
				}, {
					field: "bookPress",
					minWidth: 130,
					title: "出版社",
					align: "center"
				},
				{
					field: "bookNumber",
					title: "编号",
					width: 180,
					align: "center"
				}, {
					field: "bookRegion",
					title: "位置",
					width: 120,
					align: "center"
				},{
					title: "操作",
					align: "center",
					fixed: "right",
					toolbar: "#table-book-delete"
				}
			]
		],
		//不传分页条的话就是默认10
		page: !0,
		limit: 5,
		limits: [5, 10, 15, 20],
		text: "对不起，加载出现异常！"
	}), l.on("tool(LAY-book-manage)", function(e) {
		var data = e.data;//获得当前行数据
		
		if(e.event === 'del'){
			layer.confirm('确定要删除这本书吗?', function(index){
			  $.ajax({
			  	url:"/deleteBookById",
			  	data:{"bookId":data.bookId},
			  	dataType: "json",
			  	success:function(data){
			  		if(data.code==0){
			  			layer.msg("删除成功");
			  			layui.table.reload('LAY-book-manage');
			  		}else{
			  			layer.msg("删除失败");
			  		}
			  	}
			  });
			  layer.close(index);
			});
		}
		
	}), e("bookManage", {})
});
