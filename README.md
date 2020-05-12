# 简介

#### 实现调用抖音11.0.0 libcms.so的leviathan方法生成x-gorgon
#### 调用代码见https://github.com/satnglove/sign-algorithms

#### 用法
![](./png/1.png "")

```
libcms.so md5 446ea6cb626b3f3fdb2607f398e99f3e
```

```python

import requests
import time

rticket = int(time.time() * 1000)
url = f"https://aweme.snssdk.com/aweme/v1/aweme/favorite/?invalid_item_count=0&is_hiding_invalid_item=0&max_cursor=0&sec_user_id=MS4wLjABAAAAxcFxZ_rO4A_KJdKlpxkbaf3338mcezcAH6tc8xvev5M&count=20&os_api=22&device_type=SM-G9350&ssmix=a&manifest_version_code=100901&dpi=192&uuid=865851147329023&app_name=aweme&version_name=10.9.0&ts=1588844919&app_type=normal&ac=wifi&host_abi=armeabi-v7a&update_version_code=10909900&channel=wandoujia_aweme_feisuo&_rticket={str(rticket)}&device_platform=android&iid=4212964517742397&version_code=100900&cdid=71403db1-8dac-44f8-a63f-17e5a6493496&openudid=9c1f84a49d5b008b&device_id=71455100727&resolution=720*1280&os_version=5.1.1&language=zh&device_brand=samsung&aid=1128&mcc_mnc=46003"
# 1.clone https://github.com/heyaug/HttpSo项目
# 2.编译app并安装
# adb forward tcp:8887 tcp:8888

gorgon = requests.post("http://192.168.1.8:8888/url", data=url).text
print("gorgon:" + gorgon)
headers = {
	"X-SS-REQ-TICKET": str(rticket),
	"sdk-version": "1",
	"Cookie": "install_id=4212964517742397; ttreq=1$d508cd56f0174ab5f688fc6f43e74fb6c92f10e6; odin_tt=4d20af5c89b0b3261975bec2c6e292918d8b655498c3218ecebe8c25488f05236932f32a4270267f5c9f2916ddfdf5e3c92de9e86c90f58b8383b3f13df1b972; SLARDAR_WEB_ID=d7fd01c7-5acd-4565-aa30-6ca7cf14f705",
	"Host": "aweme.snssdk.com",
	"Connection": "Keep-Alive",
	"User-Agent": "okhttp/3.10.0.1",
	"X-Gorgon": gorgon,
	"X-Khronos": str(int(rticket / 1000))
}
response = requests.get(url, headers=headers)
print(response.text)

```

