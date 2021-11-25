[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.yvasyliev/deezer-api/badge.svg?&kill_cache=1)](https://search.maven.org/artifact/com.github.yvasyliev/deezer-api)
![Build status](https://github.com/yvasyliev/deezer-api/actions/workflows/build-maven-project.yml/badge.svg?branch=master)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/yvasyliev/deezer-api/blob/master/LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat)](http://makeapullrequest.com)
[![javadoc](https://javadoc.io/badge2/com.github.yvasyliev/deezer-api/javadoc.svg)](https://javadoc.io/doc/com.github.yvasyliev/deezer-api)
# Deezer API Java Library
A Java implementation of [Deezer API](https://developers.deezer.com/api).
## Third-party dependencies
- SLF4J
- Gson
## Requirements
1. Java 8+
2. Maven (or other build tool)
## Quickstart
1. Add to your pom.xml:
```xml
<dependency>
    <groupId>com.github.yvasyliev</groupId>
    <artifactId>deezer-api</artifactId>
    <version>1.0.1</version>
</dependency>
```
2. Start the using of Deezer API:
```java
DeezerApi deezerApi = new DeezerApi();

Album album = deezerApi.album().getById(302127).execute();
System.out.println(album);

TrackData trackData = deezerApi.search().searchTrack("eminem").execute();
System.out.println(trackData);
```
## OAuth
Some Deezer API requests should be executed with `access_token` param passed.<br/>
To obtain `access_token` your application must be registered and the user must be authorized.<br/>
To register your app:
1. Go to https://developers.deezer.com/myapps 
2. Create new Application.

Now you are ready to authorize the user. Deezer uses OAuth 2.0 protocol for authentication and authorization.<br/>
*Important:* you must have a domain and a server available to recieve the `code` after user authorization (e.g. SpringBoot + ngrok).
To authorize the user:
1. Create login URL:
```java
int appId = 123;
String redirectUri = "your.domain.com";

DeezerApi deezerApi = new DeezerApi();

String loginUrl = deezerApi.auth().getLoginUrl(appId, redirectUri, Permission.BASIC_ACCESS);
System.out.println(loginUrl); // https://connect.deezer.com/oauth/auth.php?app_id=123&redirect_uri=your.domain.com&perms=basic_access
```
2. Open `loginUrl` in your browser and login to Deezer.
3. Accept application permissions.
4. You will be redirected to `redirectUri` and the `code` will in URL parameters:<br/>
`http://redirect_uri?code=A_CODE_GENERATED_BY_DEEZER`
5. Create a request to get `access_token`:
```java
int appId = 123;
String secret = "secret_string";
String code = "A_CODE_GENERATED_BY_DEEZER";

AccessToken accessToken = deezerApi.auth().getAccessToken(appId, secret, code).execute();
System.out.println(accessToken);
```

Now all Deezer API requests will be available for you:
```java
String accessToken = "access_token";
DeezerApi deezerApi = new DeezerApi(accessToken);

User me = deezerApi.user().getMe().execute();
System.out.println(me);

TrackData favouriteTracks = deezerApi.user().getFavouriteTracks(me.getId()).execute();
System.out.println(favouriteTracks);
```
