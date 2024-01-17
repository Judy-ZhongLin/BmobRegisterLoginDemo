# BmobRegisterLoginDemo
以Bmob为云端储存平台的demo

1. 在http://www.bmobapp.com/ 上新申请账号（现在好像是免费3个月 后续需要缴费使用了）
2. 创建新应用，根据User类内容增加列
3. 进入 应用Key
  ![image](https://github.com/Judy-ZhongLin/BmobRegisterLoginDemo/assets/83565231/2e6fb890-a177-40e0-99f1-728c680e7de4)
4. 将主事件MainActivity 22行 Bmob.initialize(this, "6f8a7eed76aa4d0f8dfd83cd746ba8a0");改成你申请的Application ID 即可正常使用
