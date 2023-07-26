from concurrent.futures import ThreadPoolExecutor
import time


def get_html(times, name):
    time.sleep(times)
    print("get page {} success".format(times))
    return name

print(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())))
# 表示在这个线程池中同时运行的线程有3个线程
executor = ThreadPoolExecutor(max_workers=5)

# 通过submit函数提交执行的函数到线程池中, submit 是立即返回
task1 = executor.submit(get_html, 1, "ss")  # 第一个是回调函数，第二个是传给函数的参数
task2 = executor.submit(get_html, 2, "ss")
task3 = executor.submit(get_html, 3, "ss")
task4 = executor.submit(get_html, 4, "ss")
task5 = executor.submit(get_html, 5, "ss")
task6 = executor.submit(get_html, 6, "ss")


print(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())))