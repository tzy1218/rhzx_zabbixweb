
	function download() {   //下载主函数
		var url = "excel";
		var starttime = $("#startdatetimepicker").prop("value");
		var endtime = $("#enddatetimepicker").prop("value");
		var data = {"starttime": starttime, "endtime": endtime};
		// console.log(data);
	// if( $("input[name='alter_total']").attr("checked") == "checked" ) {
		if( $("input[name='alter_total']").prop("checked") ) {
			raypost("getAlertExcel",data,dingzhialert1);
		}
		if( $("input[name='perfor_total']").prop("checked") ) {
			raypost("getPerfExcel",data,dingzhialert1);
		}
		if( $("input[name='monit_total']").prop("checked") ) {
			raypost("getMonitExcel",data,dingzhialert1);
		}
	}
	function raypost(url, data_s, callback) {   //ajax. post方法
		$.ajax({
			url: url,
			type: "POST",
			data: data_s,
			success: function(data){
				callback(data);
			},
			error: function(err){
				callback(); // for test when 404!!!!!!!!!!
				console.log(err);
				return false;
			},
		})
	}
	function rayget(url, callback) {    // ajax get方法
		$.ajax({
			url: url,
			type: "GET",
			success: function(data){
				callback(data);
			},
			error: function(err){
				callback(); // for test when 404!!!!!!!!!!
				console.log(err);
				return false;
			},
		})
	}
	function donothin(data) {     // callback 之 donothing
		console.log(data);
		return true;
	}
	var dingzhialert1 = function(data){    // callback ajax. success执行函数
		var filename = data;
		window.open("getExcel?fileName="+filename,"","");
//		var filename = JSON.stringify(data);
//		console.log(filename);
//		window.open("file:///home/hadoop/Documents/eclipse/201804010800201804040801.xls");
//		window.open("ServletDownload?fileName=" + filename,"","");
		
//		$("#dingzhialert").append("<p>告警汇总表定制完成</p>");	
//		var starttime = $("#startdatetimepicker").prop("value");
//		var endtime = $("#enddatetimepicker").prop("value");	
//		$(".maincontext table[name='bbtab_unfinished'] tbody").append("<tr><td>告警汇总</td><td>"+starttime+"</td><td>"+endtime+"</td><td>ing...</td></tr>");
	};
	var dingzhialert2 = function(){
		$("#dingzhialert").append("<p>性能数据汇总表定制完成</p>");
	};
	var dingzhialert3 = function(){
		$("#dingzhialert").append("<p>性能指标汇总表定制完成</p>");
	};

	function bbDownFunc() {              // 下载报表标签 点击函数
		if( !$(".maincontext div[name='bbdownload']").hasClass('hidden') ) {
			rayget("xxx.json", wri2bbtab);
		}
	}
	function wri2bbtab(data) {       // 写入已下载函数
		var datan = JSON.stringify(data).replace(/\r\n/g,"<br>").replace(/\n/g,"<br>");       
		var dataterm  = JSON.parse(datan).data; 
		var total = JSON.parse(datan).totalnum;
		var pageTotal = total%10==0?Math.floor(total/10):Math.floor(total/10+1);
		var i = $("#pageBtn li[class='disabled'] a").html()===undefined?1:($("#pageBtn li[class='disabled'] a").html()-0);	
		console.log(i,$("#pageBtn li[class='disabled'] a").html());
		refreshdata(i,10);
	}


	// 分页相关函数
	function refreshdata(pagenum, perpagecount) {
		var start = (pagenum-1)*perpagecount;
		var count = perpagecount;
		$.ajax({  
			url:'xxx.json',  
			type:'post',  
			data: {
				'start': start,
				'count': count,
			},
			success:function(data){       
				var datan = JSON.stringify(data).replace(/\r\n/g,"<br>").replace(/\n/g,"<br>");       
				var dataterm  = JSON.parse(datan).data; 
				var total = JSON.parse(datan).totalnum;
				var ht='';
				for(var i=0; i<Math.min(dataterm.length,count); i++) {
					ht+="<tr><td>"+dataterm[i].type+"</td><td>"+dataterm[i].starttime+"</td><td>"+dataterm[i].endtime+"</td><td>"+dataterm[i].filename+"</td></tr>";
			         // console.log(dataterm,ht);		
		             //var tablebody = $(".maincontext table tbody:eq(1)"); 
		             var tablebody = $(".maincontext table[name='bbtab_finished'] tbody"); 
		             tablebody.html(ht);	}

		             var pageTotal = total%10==0?Math.floor(total/10):Math.floor(total/10+1);
		             // var i = $("#pageBtn li[class='disabled'] a").html()==undefined?pagenum:parseInt($("#pageBtn li[class='disabled'] a").html());
		               // refreshdata(i,10);
		               // console.log(i,$("#pageBtn li[class='disabled'] a"),$("#pageBtn li[class='disabled'] a").html());
		        pageination(pagenum,pageTotal,perpagecount);
		    },
	        error: function(err) {
		          console.log(err);
		      },  
	});
}  

	function pageination(dqPage,pageCount,perpagecount){       // 抽空把if else if 改成switch case

		var i = 1; i = parseInt(i);	var item="";
        if (pageCount <= 5 ) {  //总页数小于五页，则加载所有页
        	for (i; i <= pageCount; i++) {
        		if (i == dqPage) {
        			item += "<li class='disabled'><a>"+i+"</a></li>";
        		} else{
        			func="onclick='refreshdata("+i+","+perpagecount+");'";
        			item += "<li><a href='javascript:void(0);'"+func+">"+i+"</a></li>";
        		}
        	};
         } else if (pageCount > 5) {//总页数大于五页，则加载五页
          if (dqPage < 5) {//当前页小于5，加载1-5页
          	for (i; i <= 5; i++) {
          		if (i == dqPage) {
          			item += "<li class='disabled'><a>"+i+"</a></li>";
          		}else{
          			func="onclick='refreshdata("+i+","+perpagecount+");'";
          			item += "<li><a href='javascript:void(0);'"+func+">"+i+"</a></li>";     }
          		};
                if (dqPage <= pageCount-2) {//最后一页追加“...”代表省略的页
                	item += "<li><a href='javascript:void(0);'>...</a></li>";
                }
              } else if (dqPage >= 5) {//当前页大于5页
                  for (i; i <= 2; i++) {//1,2页码始终显示
                  	func="onclick='refreshdata("+i+","+perpagecount+");'";
                  	item += "<li><a href='javascript:void(0);'"+func+">"+i+"</a></li>";    }
                  	item += "<li><a href='javascript:void(0);'>...</a></li>";
                    //2页码后面用...代替部分未显示的页码
   if (dqPage+1 == pageCount) {    //当前页+1等于总页码
   for(i = dqPage-1; i <= pageCount; i++){        //“...”后面跟三个页码当前页居中显示
   	if (i == dqPage) {
   		item += "<li class='disabled'><a>"+i+"</a></li>";
   	}else{
   		func="onclick='refreshdata("+i+","+perpagecount+");'";
   		item += "<li><a href='javascript:void(0);'"+func+">"+i+"</a></li>";     }
   	}
   }else if (dqPage == pageCount) {//当前页数等于总页数则是最后一页页码显示在最后
   for(i = dqPage-2; i <= pageCount; i++){//...后面跟三个页码当前页居中显示
   	if (i == dqPage) {
   		item += "<li class='disabled'><a>"+i+"</a></li>";
   	}else{
   		func="onclick='refreshdata("+i+","+perpagecount+");'";
   		item += "<li><a href='javascript:void(0);'"+func+">"+i+"</a></li>";     }
   	}
   }else{//当前页小于总页数，则最后一页后面跟...
   for(i = dqPage-1; i <= dqPage+1; i++){//dqPage+1页后面...
   	if (i == dqPage) {
   		item += "<li class='disabled'><a>"+i+"</a></li>";
   	}else{
   		func="onclick='refreshdata("+i+","+perpagecount+");'";
   		item += "<li><a href='javascript:void(0);'"+func+">"+i+"</a></li>";     }
   	}
   	item += "<li><a href='javascript:void(0);'>...</a></li>";
   }
}
}
// funcpre="onclick='pageination("+(dqPage>=2?dqPage-1:1)+","+pageCount+");'";
// funcnex="onclick='pageination("+(dqPage<pageCount?dqPage+1:pageCount)+","+pageCount+");'";
funcpre="onclick='refreshdata("+(dqPage>=2?dqPage-1:1)+","+perpagecount+");'";
funcnex="onclick='refreshdata("+(dqPage<perpagecount?dqPage+1:perpagecount)+","+perpagecount+");'";


item = "<li><a href='javascript:void(0);'"+funcpre+">Prev</a></li>"+item+"<li><a href='javascript:void(0);'"+funcnex+">Next</a></li>";
$('#pageBtn').html(item);
}


