<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <form class="layui-form">
            <div class="layui-form-item " style="margin-top: 20px">
                <label class="layui-form-label">接口路径：<span style="color:red;">*</span></label>
                <div class="layui-input-inline" style="width: 80px">
                    <select name="reqType" id="reqType" class="layui-select" lay-verify="required">
                        <option value="http" selected>HTTP</option>
                        <option value="https">HTTPS</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 80px">

                    <select name="method" id="method" class="layui-select" lay-verify="required">
                        <option value="get">GRT</option>
                        <option value="post" selected>POST</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 500px">

                    <input name="path" type="text" autocomplete="off" id="path"
                           class="layui-input">
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend><span style="color:green;">|</span>
                    Request设置:
                </legend>
            </fieldset>
            <label class="layui-form-label">body:<span style="color:red;">*</span></label>
            <div class="layui-input-block">
                        <textarea name="body" placeholder="" class="layui-textarea"
                                  style="width: 500px"></textarea>
            </div>
        </form>
    </div>
</nav>