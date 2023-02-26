[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.yvasyliev/deezer-api/badge.svg?&kill_cache=1)](https://search.maven.org/artifact/com.github.yvasyliev/deezer-api)
![Build status](https://github.com/yvasyliev/deezer-api/actions/workflows/build-maven-project.yml/badge.svg?branch=master)
[![Known Vulnerabilities](https://snyk.io/test/github/yvasyliev/deezer-api/badge.svg)](https://snyk.io/test/github/yvasyliev/deezer-api)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/yvasyliev/deezer-api/blob/master/LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat)](http://makeapullrequest.com)
[![javadoc](https://javadoc.io/badge2/com.github.yvasyliev/deezer-api/javadoc.svg)](https://javadoc.io/doc/com.github.yvasyliev/deezer-api)

# Deezer API Java Library

A Java implementation of [Deezer API](https://developers.deezer.com/api).

## Third-party dependencies

- `SLF4J`
- `Gson`
- `OkHttp`

## Requirements

1. Java 8+
2. Maven (or other build tool)

## Quickstart

1. Add to your pom.xml:

```xml

<dependency>
    <groupId>com.github.yvasyliev</groupId>
    <artifactId>deezer-api</artifactId>
    <version>2.0.1</version>
</dependency>
```

2. Create and execute Deezer API requests:

```java
public class DeezerApp {
    public static void main(String[] args) throws DeezerException {
        DeezerApi deezerApi = new DeezerApi();

        Album album = deezerApi.album().getById(302127).execute();
        System.out.println(album);

        TrackData trackData = deezerApi.search().searchTrack("eminem").execute();
        System.out.println(trackData);
    }
}
```

## OAuth

Some Deezer API requests should be executed with `access_token` param passed.<br/>
To obtain `access_token` our application must be registered and the user must be authorized.<br/>
To register new app:

1. Go to https://developers.deezer.com/myapps
2. Create new Application.

Now we are ready to authorize the user. Deezer uses OAuth 2.0 protocol for authentication and authorization.<br/>

### Authorization flow example

```java
public class DeezerApp {
    /**
     * Can be found at https://developers.deezer.com/myapps
     */
    private static final int APP_ID = 123;

    /**
     * Can be found at https://developers.deezer.com/myapps
     */
    private static final String SECRET = "secret_string";

    /**
     * Your domain where user will be redirected to.
     */
    private static final String REDIRECT_URI = "your.domain.com";

    public static void main(String[] args) throws DeezerException {
        DeezerApi deezerApi = new DeezerApi();

        // Step 1. Create login URL.
        String loginUrl = deezerApi.auth().getLoginUrl(APP_ID, REDIRECT_URI, Permission.BASIC_ACCESS);
        System.out.println(loginUrl); // https://connect.deezer.com/oauth/auth.php?app_id=123&redirect_uri=your.domain.com&perms=basic_access

        /*
         * Step 2. Authorize.
         *
         *      1. Open loginUrl in your browser.
         *      2. Login to Deezer. (if haven't done yet)
         *      3. Accept application permissions.
         *      4. Find a 'code' parameter in URL after redirection to:
         *          http://redirect_uri?code=A_CODE_GENERATED_BY_DEEZER
         */
        System.out.print("Please enter code: ");
        String code = new Scanner(System.in).next();

        // Step 3. Get access_token.
        DeezerAccessToken accessToken = deezerApi.auth().getAccessToken(APP_ID, SECRET, code).execute();
        deezerApi.setAccessToken(accessToken);

        // Now we are ready to execute any request we want.
        User me = deezerApi.user().getMe().execute();
        System.out.println(me);

        TrackData favouriteTracks = deezerApi.user().getFavouriteTracks(me.getId()).execute();
        System.out.println(favouriteTracks);
    }
}
```
