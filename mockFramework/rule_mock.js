global.api = "testapi";   //mock接口标识
module.exports = {

    summary:function(){
        return "replace response data by local response now";
    },

    //mark if use local response
    shouldUseLocalResponse : function(req,reqBody){
      var fs=require('fs');
      var msdata=fs.readFileSync('./conf/ms.json',"UTF-8"); //读取mock开关配置文件
      var ms = JSON.parse(msdata);
      var isOpen = ms['isOpen'];
      if (isOpen==0) {                               //当isOpen为0时，把直接返回false跳过所有mock
        console.log("isOpen is :" +isOpen+",skip mock!");
        return false;
      };
      var file = fs.readFileSync('./api/mock_api.txt',"utf8");  //读取api文件
      var apilist = [];
      apilist=file.split(/\r?\n/ig);                 //按行分割存为列表
      for (var i = 0;  i < apilist.length-1; i++) {  //循环mock，有多少个接口就mock多少次，支持多接口同时mock
        var mockapi=apilist[i];
        if(new RegExp(mockapi).test(req.url)){       //匹配是否为被mock接口
          global.api=mockapi;
          return true;
          }
        };
      return false;  
    },

    dealLocalResponse : function(req,reqBody,callback){
            var fs=require('fs');
            //var xpath = require('xpath');
            //var dom = require('xmldom').DOMParser;
            var libxmljs = require("libxmljs");

            var xmlDoc = libxmljs.parseXml(reqBody);
            var gchild = xmlDoc.get('//test');
            console.log(gchild.text());

            //var express=require('express');
            //var bodyParser=require('body-parser');
            //var xml2json=require('xml2json');

            console.log('mock api is: '+global.api); //使用global对象访问到"全局"变量
            fs.readFile('./data/mock_data.json',function(err,data){  //读取mock的数据，data为一个json对象
                if(err){  
                    throw err;
                }     
                var arr = JSON.parse(data);
                try{
                  var respon=arr[global.api];  //取出json对象中对应api的数据
                  console.log("req.body  :" + reqBody);
                  console.log("status  :" + respon.status);
                  console.log("headers  :" + JSON.stringify(respon.headers));
                  console.log("body  :" + respon.body);
                  //var newDataStr=JSON.stringify(respon.body);
                  //callback(respon.status,respon.headers,newDataStr); //组合成一个全新的响应对象并返回
				  
		  respon.body=respon.body.replace('abc',gchild.text());
                  setTimeout(resp,10,reqBody,respon,callback);
                  //callback(respon.status,respon.headers,respon.body);
                }catch(e){
                  console.log(e);
                  console.log("you should go to set mock data for api: "+global.api);  //作异常处理，当被mock的接口没有对应的mock数据时，提示并防止服务器退出
                }
            })

    }

};

function resp(reqBody,respon,callback){
     callback(respon.status,respon.headers,respon.body);
}
