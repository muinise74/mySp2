let main = {
    init : function() {
        let _this = this;
        $('#btn-update').on('click',function () {
            _this.update();
        });
        $('#btn-delete').on('click',function () {
            _this.delete();
        });
    },
    update : function () {
        let data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 수정 되었습니다.');
            window.location.href="/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {

        let id = $('#id').val();

        $.ajax({
            type : 'POST',
            url : '/api/v1/postsDelete/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
        }).done(function () {
            alert('글이 삭제 되었습니다.');
            window.location.href="/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
};

main.init();