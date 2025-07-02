# -*- coding: utf-8 -*-
import frida, sys, os

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] 消息: {}".format(message['payload']))
    elif message['type'] == 'error':
        print("[!] 错误: {}".format(message['stack']))


if __name__ == '__main__':
    # 目标应用包名
    # target_package = 'com.zotci.myapplication'
    target_package = "MT管理器"

    # 读取同目录下的hook.js文件内容
    jscode = str(open('hook.js',encoding = 'utf-8').read())

    process = frida.get_usb_device().attach(target_package)
    script = process.create_script(jscode)
    script.on('message', on_message)
    print('[*] Running CTF')
    script.load()
    sys.stdin.read()
