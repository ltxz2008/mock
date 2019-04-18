var fs=require('fs');
var fastXmlParser = require("fast-xml-parser");
global.api = "testapi";   //mock接口标识
module.exports = {

    summary:function(){
        return "replace response data by local response now";
    },

    //mark if use local response
    shouldUseLocalResponse : function(req,reqBody){
        if(req.url.includes("crpay/httpEpay")){       //匹配是否为被mock接口
            return true;
            }
        return false;
    },

    dealLocalResponse : function(req,reqBody,callback){
        var options = {
            attrPrefix : "@_",
            textNodeName : "#text",
            ignoreNonTextNodeAttr : true,
            ignoreTextNodeAttr : true,
            ignoreNameSpace : true,
            ignoreRootElement : false,
            textNodeConversion : true,
            textAttrConversion : false,
            arrayMode : false
        };
        var requestData = reqBody.toString();
        //console.log(requestData);
        var jsonObj;
        try {
            jsonObj = fastXmlParser.parse(requestData,options);
        } catch (e) {
            console.log("ERROR, XML parse failed.");
        }
        if (!jsonObj) {
            throw new Error("XML parse failed");
        }

        var temp= requestData.match(/<Message\s+id="([^"]+)"/);
        var messageId = temp && temp[1];
        if (!messageId) {
            console.log('ERROR, no messageId');
        }
        //var flowNo=jsonObj.CRBank.Message.SignReq.flowNo;
        
        var response = {
            status: 200
        };
        var body, headers;

        if(messageId.startsWith("B0001")){
            //console.log("==================true");
	    var date=jsonObj.CRBank.Message.SignReq.date;
            headers = {
                "content-type": "text/xml;charset=UTF-8",
                "Content-Length":531
            };
            body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><CRBank><Message id=\"flowNoRes\"><SignRes id=\"SignRes\"><bankId>crbank</bankId><tranCode>B0001</tranCode><flowNo>flowNoRes</flowNo><date>dateRes</date><signNo>e88dd392261e0f793c0bb1e9800fca43</signNo><accLev>X</accLev><remark1/></SignRes></Message><Signature>Pt82oASafwIYv3QJ4pnUImG1v17MBKQuRLp9Ird+zEDFhvTRujVB6iVRgUUOrI1eyy7mAQBLsmvpiaNyJTfWDOLjCp2rzjHkyDV4XqVWkys2PIkyDYW0oCI2vdQ/lnim7EIRfX0hjQgGJZ+ll3H/3+wkUh4LE0TXvjeDLKGtCXk=</Signature></CRBank>";
            body = body.replace(/flowNoRes/g, messageId).replace("dateRes", date);
        }
        //else if(jsonObj["CRBank"]["Message"]["SignReq"]["livingBody"]!=undefined){
        else if(messageId.startsWith("B0002")){
            //console.log("==================2222");
			var date=jsonObj.CRBank.Message.SCReq.date;
            headers = {
                "content-type": "text/xml;charset=UTF-8",
                "Content-Length":404
            };
            body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><CRBank><Message id=\"flowNoRes\"><SCRes id=\"SCRes\"><bankId>crbank</bankId><tranCode>B0002</tranCode><date>dateRes</date></SCRes></Message><Signature>KD1WNsxNZ7eaTU6trgesMxy6IWJoKEn94zTvaxAUzAMPRe3ABVAViOQ03tb3dB8otIP0yaJg6ELFXHWNzbL76rw6H4U3vs4p/6q7wTllB3h6rxmRgl664lTftbTFxVPTDjLrpdEs8ayZp5nPKeMAgYN5gjzDPy5L2HFvvqq6ZvE=</Signature></CRBank>";
            body = body.replace(/flowNoRes/g, messageId).replace("dateRes", date);
        }
        response.headers = headers;
        response.body = body;

        try{
            //console.log("body:"+response.body);
            setTimeout(resp,0,reqBody,response,callback);
        }catch(e){
            console.log(e);
            console.log("you should go to set mock data for api");  //作异常处理，当被mock的接口没有对应的mock数据时，提示并防止服务器退出
        }
}};


function resp(reqBody,respon,callback){
     callback(respon.status,respon.headers,respon.body);
}
