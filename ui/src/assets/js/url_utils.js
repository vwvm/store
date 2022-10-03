export function getUrlParam(key) {
    const url = decodeURI(window.location.toString()) ;
    const arr = url.split("?");
    if (arr.length > 1){
        const params = arr[1].split("&");
        for (let i = 0; i < params.length; i++) {
            const param = params[i];
            if (param.split("=")[0] === key){
                return param.split("=")[1]
            }
        }
    }
    return null;
}
