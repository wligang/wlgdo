var upload = {
    _o: null,//对象id
    _auto: true,//是否自动上传
    _yl: false,//预览
    _ylFun: null,//预览回调函数
    _ys: 100,//压缩 （1-100）100不压缩
    _sf: 100,//缩放（1-100）100不缩放

    img: null,
    mImg: null,

    init: function (o, auto, yl, ylFun, ys, sf) {
        this._o = o;
        if (auto != undefined) this._auto = auto;
        if (yl != undefined) this._yl = yl;
        if (ylFun != undefined) this._ylFun = ylFun;
        if (ys != undefined) this._ys = ys;
        if (sf != undefined) this._sf = sf;

        this.click();
    },
    click: function () {
        var o = document.getElementById(this._o);
        o.addEventListener('change', this.change, false);
    },
    change: function () {
        var oFile = this.files[0];
        var FileInfo = {
            name: oFile.name || oFile.fileName,
            type: oFile.type || oFile.fileType,
            size: oFile.size || oFile.fileSize,
            modTime: oFile.lastModified,
            blob: oFile
        };

        // Android下读不到type信息，从文件名中解析    
        if (!FileInfo.type) {
            var ext = FileInfo.name.split(".").pop().toLowerCase();
            if (ext == 'jpg') { sFileType = 'image/jpeg'; }
            else { sFileType = 'image/' + ext;}
        }
        // 读取文件大小、修改时间等信息    
        var rFilter = /^(image\/jpeg|image\/png|image\/gif|image\/bmp)$/i;
        if (!rFilter.test(FileInfo.type)) {
            return;//非图片
        }

        var oImg = document.createElement('img');
        // 使用FileReader读取
        var oReader = new FileReader();
        oReader.onload = function () {
            var sBase64 = this.result;
            // 部分Android下base64字符串格式不完整    
            if (window.gIsAndroid && sBase64.indexOf("data:image/") != 0) {
                sBase64 = sBase64.replace("base64,", FileInfo.type + ";base64,");
            }
            oImg.src = sBase64;
            upload.img = oImg;
            if (upload != 100) {
                upload.img = upload.compress(FileInfo.type);
            }
            if (upload._yl) {
                upload._ylFun(upload.img);
            }
            sBase64 = null;
        }
        oReader.readAsDataURL(oFile);
    },
    compress: function (mime_type) {
        var cvs = document.createElement('canvas');
        //naturalWidth真实图片的宽度
        var w = this.img.naturalWidth * this._sf / 100;
        var h = this.img.naturalHeight * this._sf / 100;
        cvs.width = w;
        cvs.height = h;

        var ctx = cvs.getContext("2d");
        ctx.drawImage(this.img, 0, 0, w, h);
        var newImageData = cvs.toDataURL(mime_type, this._ys / 100);
        var result_image_obj = new Image();
        result_image_obj.src = newImageData;
        return result_image_obj;
    }
};