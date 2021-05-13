// app.js
App({
  onLaunch() {
    var that=this
    wx.login({
      success: function (res) {
        var code = res.code;
        var appId = 'wx33009fe8642899cb';
        var secret = '244e64c3abb1867721c392443ebd530c';
        wx.request({
          url: 'https://api.weixin.qq.com/sns/jscode2session?appid=' + appId + '&secret=' + secret + '&js_code=' + code + '&grant_type=authorization_code',
          header: {
            'content-type': 'json'
          },
          success: function (res) {
            var openid = res.data.openid
            wx.request({
              url: 'http://localhost:8083/jbyEng/login',
              data:{
                id:openid,
              },
              method: 'GET',
              header: {
                'content-type': 'application/json'
              },
              success: function (res) {
                console.log("login success")
                that.globalData.user_id=openid
              }
            })
          }
        })
      }
    })
  },
  globalData: {
    user_id:''
  }
})
