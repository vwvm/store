import requests

from onlineTranslation在线翻译.apidemo.utils.AuthV3Util import addAuthParams


class Translate:

    def __init__(self, source):
        super().__init__()
        # 您的应用ID
        self.APP_KEY = '75bad38543d87918'
        # 您的应用密钥
        self.APP_SECRET = 'SRugYV0SRIcs6kYvLUSSUpLG1Su4Rjaj'
        self.translation = ''
        self.basic = ''
        self.createRequest(source)

    def createRequest(self, value: str):
        q = '你好吗'
        if value != '':
            q = value
        lang_from = '源语言语种'
        lang_to = '目标语言语种'
        vocab_id = '您的用户词表ID'

        data = {'q': q, 'from': lang_from, 'to': lang_to, 'vocabId': vocab_id, }

        addAuthParams(self.APP_KEY, self.APP_SECRET, data)

        header = {'Content-Type': 'application/x-www-form-urlencoded'}
        res = self.doCall('https://openapi.youdao.com/api', header, data, 'post')
        data = res.json()
        self.translation = data["translation"]
        self.basic = data["basic"]

        print(self.translation)
        print(self.basic)
        print(data["web"])
        print(data["dict"])
        print(data["webdict"])

    def get_translation(self) -> str:
        return self.translation

    def get_basic(self) -> str:
        return self.basic

    def doCall(self, url, header, params, method):
        if 'get' == method:
            return requests.get(url, params)
        elif 'post' == method:
            return requests.post(url, params, header)


if __name__ == '__main__':
    res = Translate("你好啊")
    print(res.get_translation())
    print(res.get_basic())
