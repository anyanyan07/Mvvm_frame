// IWebView2MainInterface.aidl
package com.ayy.webview;

// Declare any non-default types here with import statements

import com.ayy.webview.IMain2WebViewInterface;

interface IWebView2MainInterface {
   void handleCommand(String commandName,String params,in IMain2WebViewInterface callback);
}
