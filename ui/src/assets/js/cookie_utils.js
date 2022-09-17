const operator = "=";

export function getCookieValue(keyStr){
	let value = null;
	const s = window.document.cookie;
	const arr = s.split("; ");
	for(let i=0; i<arr.length; i++){
		const str = arr[i];
		const k = str.split(operator)[0];
		const v = str.split(operator)[1];
		if(k === keyStr){
			value = v;
			break;
		}
	}
	return value;
}

export function setCookieValue(key,value){
	document.cookie = key+operator+value;
}
