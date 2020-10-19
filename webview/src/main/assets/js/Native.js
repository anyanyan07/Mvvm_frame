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

