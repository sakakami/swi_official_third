package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.CodeContent;

public interface IVPayCheck {
    void finishSend();
    void init(CodeContent codeContent, String cash);
}
