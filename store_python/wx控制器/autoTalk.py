# -*- coding: utf-8 -*-


import pandas as pd
import numpy as np

from uiautomation import WindowControl

wx = WindowControl(
    Name="微信",
)
print(wx)
wx.SwitchToThisWindow()