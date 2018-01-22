/**
 * Created by zhiwen on 2018/1/22.
 */
!(function ($) {
    $.fn.myModal = function (options) {
        var defaults = {
            "title": "标题",
            "cancelBtn": {
                "text": "取消",
                "onclick": function () {
                    alert("取消")
                }
            },
            "okBtn": {
                "text": "保存",
                "onclick": function () {
                    alert("保存")
                }
            },
            "bodyHtml": "<p>内容</p>"
        };
        var settings = $.extend({}, defaults, options);

        return this.each(function () {
            if ($(this).attr("data-target") && $(this).attr("data-toggle")) {
                return;
            }
            $(this).attr("data-target", "#exampleModalLong").attr("data-toggle", "modal");
            var html = '<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">\n' +
                '  <div class="modal-dialog" role="document">\n' +
                '    <div class="modal-content">\n' +
                '      <div class="modal-header">\n' +
                '        <h5 class="modal-title" id="exampleModalLongTitle">' + settings.title + '</h5>' +
                '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
                '          <span aria-hidden="true">&times;</span>' +
                '        </button>\n' +
                '      </div>\n' +
                '      <div class="modal-body">\n' + settings.bodyHtml +
                '      </div>\n' +
                '      <div class="modal-footer">\n' +
                '        <button type="button" class="btn btn-secondary cancel" data-dismiss="modal">' + settings.cancelBtn.text + '</button>' +
                '        <button type="button" class="btn btn-primary ok">' + settings.okBtn.text + '</button>' +
                '      </div>' +
                '    </div>' +
                '  </div>\n' +
                '</div>';
            $('body').append(html);

            $("#exampleModalLong .ok").on("click", function () {
                settings.okBtn.onclick.call(this)
            });
        });
    }
})(jQuery)



