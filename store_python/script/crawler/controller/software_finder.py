import os
import time

from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec

supported_domain_names = [
    "detail.tmall.com",
    "flbook.com.cn",
]


def detail_tmall_com(driver: WebDriver,  emitter):
    WebDriverWait(driver, 60).until(
        ec.presence_of_element_located((By.TAG_NAME, 'body'))
    )
    emitter.log_updated.emit("网页加载完成")
    time.sleep(10000)
    return None

def flbook_com_cn(driver: WebDriver, emitter, self):
    type_m = 1
    try:
        if type_m != 1:
            raise ValueError("负")
        WebDriverWait(driver, 60).until(
            ec.presence_of_element_located((By.TAG_NAME, 'body'))
        )
        emitter.log_updated.emit("网页加载完成")
        WebDriverWait(driver, 60).until(
            ec.presence_of_element_located((By.NAME, 'thumbnail'))
        )
        time.sleep(2)
        driver.find_elements(By.NAME, 'thumbnail')[0].click()

        WebDriverWait(driver, 60).until(
            ec.presence_of_element_located((By.CLASS_NAME, 'page-li'))
        )
        emitter.log_updated.emit("图片加载完成")
        page_li_elements = driver.find_elements(By.CLASS_NAME, 'page-li')
        row, col = 0, 0
        for page_li in page_li_elements:
            dhc_elements = page_li.find_elements(By.CLASS_NAME, 'dhc')
            for dhc in dhc_elements:
                style = dhc.get_attribute('style')
                if style:
                    start = style.find('url("//') + len('url("//')
                    end = style.find('?', start)
                    if start != -1 and end != -1:
                        image_url = style[start:end]
                        if not image_url.startswith(('http://', 'https://')):
                            image_url = 'https://' + image_url

                        emitter.log_updated.emit(f"下载图片：{image_url}")
                        title = driver.title
                        temp_folder = os.path.join("temp", title)
                        os.makedirs(temp_folder, exist_ok=True)
                        image_info = {'url': image_url, 'title': title, 'folder': temp_folder}
                        self.image_infos.append(image_info)
                        self.display_image(image_url, temp_folder, row, col)
                        col += 1
                        if col >= 5:
                            col = 0
                            row += 1
                else:
                    emitter.log_updated.emit(f"方案1失败，清理中")
                    break
    except Exception as e:
        print(f"加载超时: {e}")
        emitter.log_updated.emit(f"加载超时: {e}")

    try:
        emitter.log_updated.emit(f"启动方案2")
        WebDriverWait(driver, 60).until(
            ec.presence_of_element_located((By.NAME, 'prev'))
        )
        time.sleep(5)
        emitter.log_updated.emit(f"启动方案2成功")
        current_url = driver.current_url
        temp_url = "temp_url"
        row, col = 0, 0
        page_img_list = []
        while current_url != temp_url:
            col += 1
            page_img_list_temp = driver.find_elements(By.CLASS_NAME, 'pageImg')
            for page_img in page_img_list_temp:
                if page_img not in page_img_list:
                    page_img_list.append(page_img)
                else:
                    continue
                image_url = page_img.get_attribute('src')
                start = image_url.find('blob:') + len('blob:')
                end = image_url.find('?', start)
                image_url = image_url[start:]
                title = driver.title
                temp_folder = os.path.join("temp", title)
                os.makedirs(temp_folder, exist_ok=True)
                image_info = {'url': image_url, 'title': title, 'folder': temp_folder}
                self.image_infos.append(image_info)
                self.display_image(image_url, temp_folder, row, col)

            current_url = driver.current_url
            driver.find_elements(By.NAME, 'next')[0].click()
            temp_url = driver.current_url
            emitter.log_updated.emit(f"current_url{current_url}//temp_url{temp_url}")
            time.sleep(1)
        time.sleep(100)
        pass
    except Exception as e:
        emitter.log_updated.emit(f"方案2失败: {e}")
    emitter.log_updated.emit(f"采集的线程结束")