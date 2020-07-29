layui.define(['form', 'upload',"table","element"], function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,table = layui.table
  ,admin = layui.admin
  ,form = layui.form;

  var $body = $('body');
	 
	 table.render({
	   elem: '#book-borrow'
	   ,url: '/findBorrowBooksByUserId' //模拟接口
	   ,cols: [[
	     {field: 'bookId', width: 100, title: '序号',align: "center"}
	     ,{field: 'bookName', title: '书名', minWidth: 100,align: "center"}
	     ,{field: 'bookAuthor', title: '作者',align: "center"}
	     ,{field: 'bookBorrowDate', title: '借阅时间',templet:"<div>{{layui.util.toDateString(d.bookBorrowDate,'yyyy-MM-dd')}}</div>",align: "center"}
	     ,{field: 'bookReturnDate', title: '归还时间', templet:"<div>{{layui.util.toDateString(d.bookReturnDate,'yyyy-MM-dd')}}</div>",align: "center"}
	     ,{title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#book-return-script'}
	   ]]
	   ,page: true
	   ,limit: 10
	   ,limits: [10, 15, 20, 25, 30]
	   ,text: '对不起，加载出现异常！'
	 });
	 
	 table.on('tool(book-borrow)', function(obj){
	   var data = obj.data;
	   if(obj.event === 'return-book'){
	     layer.confirm('确定要还这本书吗?', function(index){
	       $.ajax({
	       	url:"/returnBookByUserId",
	       	data:{"bookId":data.bookId},
	       	dataType: "json",
	       	success:function(data){
	       		if(data.code==0){
	       			layer.msg("还书成功");
	       			layui.table.reload('book-borrow');
	       		}else{
	       			layer.msg("还书失败");
	       		}
	       	}
	       });
	       layer.close(index);
	     });
	   } 
	 });
  
  //对外暴露的接口
  exports('userInfo', {})
});