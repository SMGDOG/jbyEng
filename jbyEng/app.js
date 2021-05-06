// app.js
App({
  onLaunch() {
    var that=this
    wx.request({
      url: 'http://localhost:8083/jbyEng/login',
      data:{
        id:that.globalData.user_id,
        name:that.globalData.user_name
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("success")
      }
    })
  },
  globalData: {
    user_id: "475869",
    user_name: "lsb"
  }
})
