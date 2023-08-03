class Main:
    def __init__(self):
        self.text_hallo = ""
        pass

    def perform_operation(self, operation):
        self.text_hallo = "ddda"
        result = operation()
        return result

    def print_str(self, text: str):
        print(text)

    def run(self):
        self.text_hallo = "表达式作为回调函数"
        result_add = self.perform_operation(lambda: self.print_str(self.text_hallo))


if __name__ == '__main__':
    m = Main()
    m.run()
