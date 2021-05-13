// pages/search/search.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    word:'',
    pron_a:'',
    pron_e:'',
    type:'',
    tran:''
  },

  search_input: function (e) {
    this.setData({
      word: e.detail.value
    })
  },

  searchbutton: function(res){
    var that=this
    wx.request({
      url: 'http://localhost:8083/jbyEng/searchWord',
      data:{
        word:that.data.word
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("search success")
        var word=that.data.word
        var pron_a=res.data.美式发音
        var pron_e=res.data.英式发音
        var type=res.data.词性
        var tran=res.data.翻译
        if(pron_a==null){
          pron_a="无"
        }
        if(pron_e==null){
          pron_e="无"
        }
        if(type==null){
          type="无"
        }
        if(tran==null){
          tran="无"
        }
        that.setData({
          word:word,
          pron_a:pron_a,
          pron_e:pron_e,
          type:type,
          tran:tran
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})