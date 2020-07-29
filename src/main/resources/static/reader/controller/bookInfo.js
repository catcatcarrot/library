/** layuiAdmin.pro-v1.2.1 LPPL License By http://www.layui.com/admin/ */ ;
layui.define(["table", "form", "element"], function(e) {
    var t = (layui.$, layui.admin),
        i = layui.view,
        r = layui.table,
        l = layui.form,
        o = layui.element;
    r.render({
        elem: "#computer",
        url: "/getBooksBySort",
		where:{"sort":"0"},
        method:"GET",
        cols: [
            [{
                field: "bookId",
                width: 80,
                title: "序号",
                align: "center"
            },{
                field: "bookName",
				width: 300,
				title: "书名",
				align: "center"
            }, {
                field: "bookAuthor",
                width: 120,
                align: "center",
                title: "作者"
            }, {
                field: "bookPress",
                minWidth: 180,
                title: "出版社",
                align: "center"
            }, {
                field: "bookNumber",
                title: "编号",
                width: 180,
                align: "center"
            }, {
                 field: "bookRegion",
                 title: "位置",
                 width: 120,
                 align: "center"
            } ]
        ],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！",
        done: function() {
            o.render("progress")
        }
    });
	r.render({
        elem: "#literature",
        url: "/getBooksBySort",
		where:{"sort":"1"},
        method:"GET",
        cols: [
        [{
            field: "bookId",
            width: 80,
            title: "序号",
            align: "center"
        },{
            field: "bookName",
            width: 300,
            title: "书名",
            align: "center"
        }, {
            field: "bookAuthor",
            width: 120,
            align: "center",
            title: "作者"
        }, {
            field: "bookPress",
            minWidth: 180,
            title: "出版社",
            align: "center"
        }, {
            field: "bookNumber",
            title: "编号",
            width: 180,
            align: "center"
        }, {
             field: "bookRegion",
             title: "位置",
             width: 120,
             align: "center"
        } ]
        ],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！",
        done: function() {
            o.render("progress")
        }
    }), r.render({
        elem: "#life",
        url: "/getBooksBySort",
		where:{"sort":"2"},
        method:"GET",
        cols: [
           [{
               field: "bookId",
               width: 80,
               title: "序号",
               align: "center"
           },{
               field: "bookName",
            width: 300,
            title: "书名",
            align: "center"
           }, {
               field: "bookAuthor",
               width: 120,
               align: "center",
               title: "作者"
           }, {
               field: "bookPress",
               minWidth: 180,
               title: "出版社",
               align: "center"
           }, {
               field: "bookNumber",
               title: "编号",
               width: 180,
               align: "center"
           }, {
                field: "bookRegion",
                title: "位置",
                width: 120,
                align: "center"
           } ]
        ],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！",
        done: function() {
            o.render("progress")
        }
    }),r.render({
         elem: "#political",
         url: "/getBooksBySort",
         where:{"sort":"3"},
         method:"GET",
         cols: [
           [{
               field: "bookId",
               width: 80,
               title: "序号",
               align: "center"
           },{
               field: "bookName",
            width: 300,
            title: "书名",
            align: "center"
           }, {
               field: "bookAuthor",
               width: 120,
               align: "center",
               title: "作者"
           }, {
               field: "bookPress",
               minWidth: 180,
               title: "出版社",
               align: "center"
           }, {
               field: "bookNumber",
               title: "编号",
               width: 180,
               align: "center"
           }, {
                field: "bookRegion",
                title: "位置",
                width: 120,
                align: "center"
           } ]
         ],
         page: !0,
         limit: 10,
         limits: [10, 15, 20, 25, 30],
         text: "对不起，加载出现异常！",
         done: function() {
             o.render("progress")
         }
     }),e("bookInfo", {})
});