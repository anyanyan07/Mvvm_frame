var nativeJs = {};
nativeJs.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
nativeJs.isAndroid = !nativeJs.isIOS;
nativeJs.takeNativeAction = function(action,parameters){
        const request = {
            action,parameters
        }
       if(nativeJs.isAndroid){
            console.log('html调用android js')
            window.android.takeNativeAction(JSON.stringify(request))
       }else{
            console.log('ios')
       }
}
nativeJs.callbacks = {}
nativeJs.callback = function(callbackName,response){
    console.log('come')
    if(nativeJs.callbacks[callbackName]!=undefined){
        console.log('1')
        nativeJs.callbacks[callbackName](response)
        console.log('2')
        delete nativeJs.callbacks[callbackName]
    }
}


nativeJs.takeNativeActionWithCallback = function(action,parameters,callback){
        const callbackName = `callbackName_${new Date().getTime()}`
        nativeJs.callbacks[callbackName] = callback
        parameters.callbackName = callbackName
        const request = {
            action,parameters
        }
       if(nativeJs.isAndroid){
            console.log('html调用android js')
            window.android.takeNativeAction(JSON.stringify(request))
       }else{
            console.log('ios')
       }
}

