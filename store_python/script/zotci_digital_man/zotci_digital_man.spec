# -*- mode: python ; coding: utf-8 -*-

from PyInstaller.utils.hooks import collect_data_files

# 自动收集 PySide6 的所有数据文件（包括 QML 文件）
qml_datas = collect_data_files('PySide6')

# 手动添加 qml 目录
qml_datas.append(('qml', 'qml'))  # ('目标路径', '源路径')

a = Analysis(
    ['zotci_digital_man.py'],
    pathex=['.'],  # 当前目录，也可以指定特定路径
    binaries=[],
    datas=qml_datas,  # 将 QML 数据文件添加到 Analysis 的 datas 中
    hiddenimports=[],
    hookspath=[],
    hooksconfig={},
    runtime_hooks=[],
    excludes=[],
    noarchive=False,
    optimize=0,
)

pyz = PYZ(a.pure)

exe = EXE(
    pyz,
    a.scripts,
    [],
    exclude_binaries=True,  # 设置为 True，以便将二进制和数据与 EXE 分开
    name='start',  # 设置生成的 exe 名称
    debug=False,
    bootloader_ignore_signals=False,
    strip=False,
    upx=True,
    upx_exclude=[],
    runtime_tmpdir=None,
    console=False,
    disable_windowed_traceback=False,
    argv_emulation=False,
    target_arch=None,
    codesign_identity=None,
    entitlements_file=None,
    icon="zotci.ico",  # 图标设置
)

coll = COLLECT(
    exe,
    a.binaries,
    a.zipfiles,
    a.datas,
    strip=False,
    upx=True,
    upx_exclude=[],
    name='zotci_digital_man',  # COLLECT 设置相同的名称
    distpath='dist',            # 可选：设置输出目录
)
