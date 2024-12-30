import requests


def send_post_request(url: str, data: dict, access_token: str) -> dict:
    """
    发送 POST 请求并返回响应 JSON 数据。
    @param url: 请求的 URL。
    @param data: 请求体中的 JSON 数据。
    @param access_token: 访问令牌，添加到请求头中。
    @return: 返回 JSON 格式的响应数据。
    """
    headers = {'Content-Type': 'application/json', 'access-token': access_token}
    try:
        response = requests.post(url, json=data, headers=headers)
        response.raise_for_status()
        response_json = response.json()  # 解析响应为 JSON 格式
        print(f"POST 请求成功，状态码: {response.status_code}, 响应: {response_json}")
        return response_json
    except requests.exceptions.RequestException as e:
        print(f"POST 请求失败: {e}")
        return {}  # 返回空字典作为失败的响应


def send_get_request(url: str, params: dict = None, access_token: str = '') -> dict:
    """
    发送 GET 请求并返回响应 JSON 数据。
    @param url: 请求的 URL。
    @param params: GET 请求的查询参数。
    @param access_token: 访问令牌，添加到请求头中。
    @return: 返回 JSON 格式的响应数据。
    """
    headers = {'access-token': access_token} if access_token else {}
    try:
        response = requests.get(url, params=params, headers=headers)
        response.raise_for_status()
        response_json = response.json()  # 解析响应为 JSON 格式
        print(f"GET 请求成功，状态码: {response.status_code}, 响应: {response_json}")
        return response_json
    except requests.exceptions.RequestException as e:
        print(f"GET 请求失败: {e}")
        return {}  # 返回空字典作为失败的响应
