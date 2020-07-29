/** layuiAdmin.pro-v1.2.1 LPPL License By http://www.layui.com/admin/ */ ;
layui.define(["table", "form"], function(t) {
	var e = (layui.$, layui.admin),
		i = layui.view,
		n = layui.table,
		l = layui.form;
		
	l.render();
	n.render({
		elem: "#LAY-search-book",
		//url: "/findByBookPress",
		method:"GET",
		//where:{"press":"西安出版社"},
		cols: [
			[{
					field: "bookId",
					title: "序号",
					align: "center"
				}, {
					field: "bookName",
					title: "书名",
					align: "center"
				},
				{
					field: "bookAuthor",
					title: "作者",
					align: "center"
				}, {
					field: "bookPress",
					title: "出版社",
					align: "center"
				},
				{
					field: "bookNumber",
					title: "编号",
					align: "center"
				}, {
					field: "bookRegion",
					title: "区域",
					align: "center"
				},
				{
					title: "操作",
					align: "center",
					fixed: "right",
					toolbar: "#borrow-book-script"
				},
				{
					field: "bookReturnDate",
					title: "归还日期",
					align: "center",
					/*
					hide: function(row){
						if(row.bookReturnDate==null)
						    return true;
						return false;
					},*/
					templet:  //"<div>{{layui.util.toDateString(d.bookReturnDate,'yyyy-MM-dd')}}</div>"
					
					
					function(row) {
                                   if(row.bookReturnDate==null){
                                     return "";                        
                                    }
                                 return row.bookReturnDate;
					}        
				}
			]
		],
		page: !0,
		limit: 10,
		limits: [10, 15, 20, 25, 30],
		text: "对不起，加载出现异常！"
	});
	
	n.on('tool(LAY-search-book)', function(obj){
	  var data = obj.data;
	  if(obj.event === 'borrow-book'){
	    layer.confirm('确定要借这本书吗?', function(index){
	      $.ajax({
	      	url:"/borrowBookByUserId",
	      	data:{"bookId":data.bookId},
	      	dataType: "json",
	      	success:function(data){
	      		if(data.code==0){
	      			layer.msg("借书成功");
	      			layui.table.reload('LAY-search-book');
	      		}else{
	      			layer.msg("借书失败");
	      		}
	      	}
	      });
	      layer.close(index);
	    });
	  } 
	});
	 
	t("searchBook", {})
});
