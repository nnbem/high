//팝업창들 뛰우기
//새로운 Window창을 Open할 경우 사용되는 함수 ( arg : 주소 , 창타이틀 , 넓이 , 길이 )
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth
							+",height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
	win.focus() ; 
}


//팝업창 닫기
function CloseWindow(){
	
	window.opener.location.reload(true);		
	window.close();
}

//회원 썸네일 : contextPath -> context path parameter
function MemberPictureThumb(contextPath){
	let elements = document.querySelectorAll('.person-info');
	for(let element of elements){
		let id = element.getAttribute("data-id");		
	
		element.style.cssText="display:block;" 
					   +"width:40px; height:40px;"
					   +"margin:0 auto; border-radius:20px;"
					   +"background-image:url('"+contextPath+"/member/getPicture?id="+id+"');"
					   +"background-position:center;"
					   +"background-size:cover;"
					   +"background-repeat:no-repeat;";		
	
	}
}

//회원 백그라운사진 : contextPath -> context path parameter
function MemberPictureBackground(contextPath){
	let elements = document.querySelectorAll('.person-info');
	for(let element of elements){
		let id = element.getAttribute("data-id");		
	
		element.style.backgroundImage ="url('"+contextPath+"/member/getPicture?id="+id+"')";
		element.style.backgroundPosition="center";
		element.style.backgroundRepeat="no-repeat";
		element.style.backgroundSize="cover";
	}
}

function Summernote_go(target,context){
	target.summernote({
        placeholder:'여기에 내용을 적으세요.',
        lang:'ko-KR',
        height:350,
        disableResizeEditor: false,
        callbacks:{
        	 onImageUpload : function(files, editor, welEditable) {
       		   for(var file of files){
                  if(file.name.substring(file.name.lastIndexOf(".")+1).toUpperCase() != "JPG"){
                     alert("JPG 이미지형식만 가능합니다.");
                     return;
                  }
                  if(file.size > 1024*1024*1){
                     alert("이미지는 1MB 미만입니다.");
                     return;
                  }         
               }
       	       for (var file of files) {
                 sendFile(file,this,context);
               }
        	 },
        	 onMediaDelete : function(target) {
        		 let fileName = target[0].src.split("=")[1];
                 //alert(fileName);
        		 deleteFile(fileName,context);
        	 }
        }
	});
}
	
function deleteFile(fileName,context) {      
   $.ajax({
      url:context+"/summernote/deleteImg?fileName="+fileName,
      success:function(res){
         console.log(res);
      },
      error:function(error){
		AjaxErrorSecurityRedirectHandler(error.status);
	  }
   });
}	
function sendFile(file,el,context){
	var form_data = new FormData();
	form_data.append("file", file);
	
	$.ajax({
		url:context+'/summernote/uploadImg',
		method:"POST",
		data: form_data,
		contentType:false,
		processData:false,
		success:function(img_url){
			//alert(img_url);
			$(el).summernote('editor.insertImage', img_url);
		},
		error:function(error){
			AjaxErrorSecurityRedirectHandler(error.status);
	    }
	});
	
	
}

function AjaxErrorSecurityRedirectHandler(status) {
   if (status == "302") {
      alert("세션이 만료되었습니다.\n로그인 하세요.");
      location.reload();
   }else if(status == "403"){
      alert("권한이 유효하지 않습니다.");
      history.go(-1);      
   }else if(status == "404"){
      alert("해당 페이지를 찾을수 없습니다.");
   }else {
      alert("시스템장애로 실행이 불가합니다.");
      history.go(-1);
   }
}

