'use strict';
var urFilename=document.querySelector("#urFilename");
var urFiledownloaduri=document.querySelector("#urFiledownloaduri");
var urFiletype=document.querySelector("#urFiletype");
var urSize=document.querySelector("#urSize");
var urSavePath=document.querySelector("#urSavePath");
var urOriginalPath=document.querySelector("#urOriginalPath");

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/TestDemo/file/uploadFile",true);

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<a id='aDown' href='" + response.urFiledownloaduri + "' target='_blank'>" + response.urFilename + "</a>";

            urFilename.value=response.urFilename;
            urFiledownloaduri.value=response.urFiledownloaduri;
            urFiletype.value=response.urFiletype;
            urSize.value=response.urSize;
            urSavePath.value=response.urSavePath;
            urOriginalPath.value=response.urOriginalPath;
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>All Files Uploaded Successfully</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

if(singleUploadForm!=null) {
    singleUploadForm.addEventListener('submit', function (event) {
        var files = singleFileUploadInput.files;

        if (files.length === 0) {
            singleFileUploadError.innerHTML = "Please select a file";
            singleFileUploadError.style.display = "block";
        }
        uploadSingleFile(files[0]);
        event.preventDefault();
    }, true);
}

if(multipleUploadForm!=null) {
    multipleUploadForm.addEventListener('submit', function (event) {
        var files = multipleFileUploadInput.files;
        if (files.length === 0) {
            multipleFileUploadError.innerHTML = "Please select at least one file";
            multipleFileUploadError.style.display = "block";
        }
        uploadMultipleFiles(files);
        event.preventDefault();
    }, true);
}

function singleUpload() {
    var files = singleFileUploadInput.files;

    if (files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
}

function multipleUpload() {
    var files = multipleFileUploadInput.files;
    if (files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    uploadMultipleFiles(files);
}