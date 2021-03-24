var myarray=["An Giang","Bà Rịa - Vũng Tàu","Bắc Giang","Bắc Kạn","Cần Thơ","Đà Nẵng","Hà Nội","Hải Phòng","Nghệ An"];
var select = document.getElementById('tinh');
for(var i=0 ; i < myarray.length; i++){
	var opt = myarray[i];
  var el = document.createElement('option');
  el.text = opt;
  el.value = opt;
  select.appendChild(el);
}