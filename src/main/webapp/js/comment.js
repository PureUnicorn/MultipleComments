var app = angular.module('app', []);

baseUrl = "";
app.controller('mainController', ['$scope', 'httpService', function($scope, httpService) {
	var mockusers = [{
		"id" : "1",
		"username" : "li",
		"avatar" : "images/li.jpg"
	}, {
		"id" : "2",
		"username" : "mario",
		"avatar" : "images/mario.jpg"
	}, {
		"id" : "3",
		"username" : "timelessmemory123",
		"avatar" : "images/timelessmemory.jpg"
	}, {
		"id" : "4",
		"username" : "smile",
		"avatar" : "images/smile.jpg"
	},]

	var currentUserIndex = 2;

	window.localStorage.setItem("id", mockusers[currentUserIndex].id);
	window.localStorage.setItem("username", mockusers[currentUserIndex].username);
	window.localStorage.setItem("avatar", mockusers[currentUserIndex].avatar);

	$scope.isShowComment = false;

	$scope.cmt = {
		fstlvlcmt : ""
	}


	//current user information
	$scope.user = {
		id : window.localStorage.getItem("id"),
		username : window.localStorage.getItem("username"),
		avatar : window.localStorage.getItem("avatar")
	}

	httpService.get("/comment/saying/get/comment/list/1", {}, function(data) {
	    $scope.data = data;
	    for(let index = 0;index<data.length;index++){
            $scope.data[index] = data[index];
            $scope.data[index].likes = $scope.data[index].likes.split(",")[0] == "" ? [] : $scope.data[index].likes.split(",");
            $scope.data[index].isShowLike = $scope.data[index].likes.contains($scope.user.id);
            $scope.data[index].isAuthor = mockusers[currentUserIndex].username == $scope.data[index].author;
        }
	}, function(error) {
		console.log(error)
	})

	$scope.like = function(saying,sayingId) {
        if (saying.likes.contains($scope.user.id)) {
            saying.likes.splice(saying.likes.indexOf($scope.user.id), 1);
        } else {
            saying.likes.push($scope.user.id)
        }

		var tmpLikes = $scope.saying.likes;
		tmpLikes = tmpLikes.join(",");

		var data = {
			id : sayingId,
			likes : tmpLikes
		};

		httpService.post("/comment/saying/likes", data, function(data) {
			$scope.isShowLike = $scope.saying.likes.contains($scope.user.id);
		}, function(error) {
			console.log(error)
		})
	}

	$scope.showComment = function(saying) {
        console.log(saying+"__"+!(saying.isShowComment));
        saying.isShowComment = !(saying.isShowComment);
	}

	$scope.Comment = function(){
	    var data = {
	        likes : mockusers[currentUserIndex].id,
            author : mockusers[currentUserIndex].username,
            avatar : mockusers[currentUserIndex].avatar,
            sayingContent : $scope.cmt.fstlvlcmt2,
            pageId : 1
        }

        httpService.post("/comment/saying/add/first", data, function(result) {
            data.createTime = result.createTime;
            $scope.cmt.fstlvlcmt = "";
            location.reload();
        }, function(error) {
            console.log(error)
        })
	}

	$scope.firstComment = function(sayingId,saying) {

		if ($scope.cmt.fstlvlcmt == "") {
			$('#hintDiv').fadeIn(300);
			
			setTimeout(function() {
				$('#hintDiv').fadeOut(300);
			}, 800);

			return;
		}

		var data = {
			sayingId : sayingId,
			commenter : $scope.user.username,
			avatar : $scope.user.avatar,
			commentContent : $scope.cmt.fstlvlcmt,
		}

		httpService.post(baseUrl + "/comment/add/first", data, function(result) {
			data.id = result.id;
			data.commentTime = result.commentTime;
			saying.flcs.push(data)
			$scope.cmt.fstlvlcmt = "";
			saying.isShowComment = false;
		}, function(error) {
			console.log(error)
		})
	}

	$scope.showSecondComment = function(comment, toWho) {
		if (!comment.isShowChildComment) {
			comment.scndlvlcmt = "@" + toWho + " ";
			comment.tmptoWho = toWho;
			comment.isShowChildComment = true;
		} else {
			if (toWho == comment.tmptoWho) {
				comment.isShowChildComment = false;
				comment.tmptoWho = "";
				comment.scndlvlcmt = "";
			} else {
				comment.scndlvlcmt = "@" + toWho + " ";
				comment.tmptoWho = toWho;
			}
		}
	}

	$scope.hideSecondComment = function(comment) {
		comment.isShowChildComment = false;
		comment.tmptoWho = "";
		comment.scndlvlcmt = "";
	}

	$scope.reply = function(sayingId, comment) {
		var scndlvlcmt = comment.scndlvlcmt;
		var str = "@" + comment.tmptoWho;
		
		if (scndlvlcmt == "" || $.trim(scndlvlcmt) == str) {
			$('#hintDiv').fadeIn(300);
			
			setTimeout(function() {
				$('#hintDiv').fadeOut(300);
			}, 800);

			return;
		}
		
		var cmt = "";

		if (scndlvlcmt.indexOf(str) >= 0) {
			cmt = scndlvlcmt.substr(scndlvlcmt.indexOf(str) + str.length)
		} else {
			cmt = scndlvlcmt;
		}

		var data = {
			sayingId : sayingId,
			flcId : comment.id,
			replier : $scope.user.username,
			toCommenter : comment.tmptoWho,
			replyContent :  cmt
		}

		httpService.post(baseUrl + "/comment/add/second", data, function(result) {
			comment.isShowChildComment = false;
			comment.scndlvlcmt = "";
			data.id = result.id;
			data.replyTime = result.replyTime;

			angular.forEach($scope.data, function(saying) {
			    angular.forEach(saying.flcs, function(flcs){
                    if (flcs.id == comment.id) {
                        flcs.slcs.push(data);
                    }
				});
			})
		}, function(error) {
			console.log(error)
		})
	}

    $scope.delete = function(sayingId){
        $('#delete1').modal("show");
        sayingID = sayingId;
    }
    $scope.confirmDel = function() {
        $('#delete1').modal("hide");
        var url = "/comment/saying/delete/" + sayingID;

        httpService.get(url, {}, function(data) {
            //local delete comment
            sayingID = "";
            flcId = "";
            location.reload();
        }, function(error) {
            console.log(error)
        })
    }

	var sayingID, flcId, slcId, saying;
	$scope.deletefslcmt = function(sayingId, firstlvlId) {
		$('#commentModal').modal("show");
		sayingID = sayingId;
		flcId = firstlvlId;
	}

	$scope.confirm = function() {
		$('#commentModal').modal("hide");
		var url = baseUrl + "/comment/delete/first/" + sayingID + "/" + flcId;
		
		httpService.get(url, {}, function(data) {
			//local delete comment
			angular.forEach($scope.data, function(saying) {
                angular.forEach(saying.flcs, function(item, index){
                    if (item.id == flcId) {
                        saying.flcs.splice(index, 1);
                    }
                });
            })
			sayingID = "";
			flcId = "";
		}, function(error) {
			console.log(error)
		})
	}

	$scope.deletescndcmt = function(sayingId, firstlvlId, secondlvlId) {
		$('#slcModal').modal("show");
		sayingID = sayingId;
		flcId = firstlvlId;
		slcId = secondlvlId;
	}

	$scope.confirmSlc = function() {
		$('#slcModal').modal("hide");

		var url = baseUrl + "/comment/delete/second/" + sayingID + "/" + slcId;
		
		httpService.get(url, {}, function(data) {
			//local delete second level comment
			angular.forEach($scope.data, function(saying){
			    angular.forEach(saying.flcs, function(flc){
                    angular.forEach(flc.slcs, function(item, index){
                        if(item.id == slcId){
                            flc.slcs.splice(index,1);
                        }
                    })
			    })
			})
			sayingID = "";
			flcId = "";
			slcId = "";
		}, function(error) {
			console.log(error)
		})
	}

}]);

app.factory('httpService', ['$http', function($http) {
  return {
    get : function(url, params, successCallback, errorCallback) {
        $http({
            url : url + "?" + $.param(params),
            method : 'get',
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
            responseType : 'json'
        })
        .success(successCallback)
        .error(errorCallback);
    },
    post : function(url, params, successCallback, errorCallback) {
      $http({
            url : url,
            method : 'post',
            data : $.param(params),
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
            responseType : 'json'
      })
      .success(successCallback)
      .error(errorCallback);
    }
  }
}]);

Array.prototype.contains = function(obj) {
    var i = this.length;

    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
