def a1(msg):
    print(msg)

if __name__ == '__main__':
    xxx = a1
    text_dict = {"aa": 3}
    for key, value in text_dict.items():
        project_name = "adv"
        prefixed_key = f"{project_name}-{key}"
        self.data[prefixed_key] = value
    print(text_dict)
