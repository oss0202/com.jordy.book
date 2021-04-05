var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function(){
           _this.save();
        });

        $('#btn-update').on('click', function (){ // (1)
            _this.update();
        })
    }
    ,save : function () {
        var data = {
            title: $('#title').val()
            ,author: $('#author').val()
            ,content: $('#content').val()
        }

        $.ajax({
            type : 'POST'
            ,url : '/api/v1/posts'
            ,dataType : 'json'
            ,contentType :'application/json; charset=utf-8'
            ,data : JSON.stringify(data)
        }).done(function (){
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
    ,update : function(){   // (2)
        var data = {
            title: $('#title').val()
            ,content: $('#content').val()
        }

        var id = $('#id').val()

        $.ajax({
            type : 'PUT'    // 3)
            ,url : '/api/v1/posts/' + id    // 4)
            ,dataType : 'json'
            ,contentType : 'application/json; charset=utf-8'
            ,data : JSON.stringify(data)
        }).done(function () {
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();