# store_python
## 初始环境配置
如果没有 poetry.lock 文件，可以直接运行 `poetry install`，Poetry 会自动生成 poetry.lock 文件并安装依赖。
```shell
  poetry install
```
如果 pyproject.toml 文件有变动，建议运行 `poetry lock` 来更新 poetry.lock 文件。
```shell
  poetry lock
```
运行以下命令激活虚拟环境，激活环境之后会输出进入环境的命令，复制之后执行即可
```shell
   poetry env activate
```
运行以下命令退出虚拟环境：
```shell
  deactivate
```
### 之后的pip操作请使用poetry
```shell
  poetry add <包名> #安装某包
  
```


## 转换
python -m PyQt5.uic.pyuic xxx.ui -o xxx.py

pyside6-uic xxx.ui -o xxx.py
