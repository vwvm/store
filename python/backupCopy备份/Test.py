import logging
import threading
import socket


class RecvMsg(threading.Thread):

    def __init__(self, udp_s: socket):
        super().__init__()
        self.udp_s = udp_s

    def run(self) -> None:
        while True:
            temp = self.udp_s.recvfrom(1024)
            logging.info(temp)

    def __del__(self):
        self.udp_s.close()


class SendMsg(threading.Thread):

    def __init__(self, udp_s: socket):
        super().__init__()
        self.udp_s = udp_s

    def run(self) -> None:
        while True:
            dest_ip = input("请输入对方的ip：")
            dest_port = int(input("请输入对方的port："))

            while True:
                send_content = input("请输入要发送的数据：")
                if send_content:
                    temp = self.udp_s.sendto(send_content.encode("utf-8"), (dest_ip, dest_port))
                else:
                    break

    def __del__(self):
        self.udp_s.close()

class ChatHistory(threading.Thread):
    def run(self) -> None:
        # 从Queue中读取数据
        pass

def main():
    udp_s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    udp_s.bind(("", 7890))

    recv_msg_thread = RecvMsg(udp_s)
    send_msg_thread = SendMsg(udp_s)

    recv_msg_thread.start()
    send_msg_thread.start()

    pass


def logging_config():
    """
    设置打印日志的级别，level级别以上的日志会打印出
    level=logging.DEBUG 、INFO 、WARNING、ERROR、CRITICAL
    此处进行Logging.basicConfig() 设置，后面设置无效
    format 设置输出格式，具体如下
        %(levelno)s 日志级别
        %(levelname)s 日志级别名称
        %(pathname)s 当前程序的路径
        %(filename)s 当前程序名称
        %(funcName)s 日志所属的当前函数
        %(lineno)d 日志的当前行号
        %(asctime)s 日志的时间
        %(thread)d 线程的ID
        %(threadName)s 线程的名称
        %(process)d 进程的ID
        %(message)s 日志的信息
    :return:
    """
    logging.basicConfig(
        format='%(asctime)s - %(name)s - %(levelname)s - %(threadName)s -%(funcName)s '
               '\n%(message)s',
        level=logging.INFO)


if __name__ == '__main__':
    logging_config()
    logging.info("测试")
    main()
