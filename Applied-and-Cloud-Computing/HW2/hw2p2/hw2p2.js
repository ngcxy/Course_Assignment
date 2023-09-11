const axios = require('axios');
const { EntityNotFoundError, ApiError } = require('./error');
const {expect} = require("chai");

class SpotifyApi {
    constructor(accessToken) {
    // complete me
        this.AccessToken = accessToken;
    }
    static async getAccessToken(clientId, clientSecret) {
        const bearer = Buffer.from(`${clientId}:${clientSecret}`).toString('base64');
        const { data: { access_token: accessToken } = {} } = await axios.post(
            'https://accounts.spotify.com/api/token',
            'grant_type=client_credentials',
            {
                headers: {
                    Authorization: `Basic ${bearer}`,
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }
        );
        return accessToken;
    }
    getArtistPromise = (artistId) => {
        return new Promise((resolve, _) => {
            this.getArtist(artistId, (_, artist) => {
                resolve(artist);
            });
        });
    };
    getTrackPromise = (trackId) => {
        return new Promise((resolve, _) => {
            this.getTrack(trackId, (_, artist) => {
                resolve(artist);
            });
        });
    };
    getAlbum(albumId, callback) {
    // web-api/reference/get-an-album
        const URL = `https://api.spotify.com/v1/albums/${albumId}`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            console.log(err);
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            callback(err);
        })
        .then(async (response) => {
            let album = {};
            let data = response.data;
            album["albumId"] = albumId;
            album["genres"] = data.genres;
            album["name"] = data.name;
            album["imageUrl"] = data.imageUrl;
            album["releaseDate"] = data.release_date;
            let tracks = [];
            let i=0;
            while (i<2 && data.tracks.items[i]!=null){
                let item = data.tracks.items[i];
                let track = {};
                try {
                    track = await this.getTrackPromise(item.id);
                    tracks.push(track);
                }catch (err) {
                    callback(err);
                }
                i++;
            }
            album["tracks"] = tracks;
            let artists = [];
            for (let item of data.artists){
                let artist = {};
                try {
                    artist = await this.getArtistPromise(item.id);
                    artists.push(artist);
                }catch (err) {
                    callback(err);
                }
            }
            album["artists"] = artists;
            callback(null, album);
        })
        .catch(err => {
            callback(err);
        })
    }
    searchAlbums(query, callback) {
    // web-api/reference/search
        const URL = `https://api.spotify.com/v1/search?q=${query}&type=album&offset=0&limit=1`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            callback(err);
        })
        .then(async (response) => {
            let albums = [];
            const getAlbumPromise = (albumId) => {
                return new Promise((resolve, _) => {
                    this.getAlbum(albumId, (_, album) => {
                        resolve(album);
                    });
                });
            };
            for (const item of response.data.albums.items){
                let album = {};
                try {
                    album = await getAlbumPromise(item.id);
                    albums.push(album);
                }catch (err) {
                    callback(err);
                }
            }
            callback(null, albums);
        })
        .catch(err => {
            callback(err);
        })
    }
    getTrack(trackId, callback) {
    // web-api/reference/get-track
        const URL = `https://api.spotify.com/v1/tracks/${trackId}`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            callback(err);
        })
        .then(async (response) => {
            let track = {};
            let data = response.data;
            track["albumId"] = data.album.id;
            track["durationMs"] = data.duration_ms;
            track["name"] = data.name;
            track["previewUrl"] = data.preview_url;
            track["popularity"] = data.popularity;
            track["trackId"] = trackId;
            let artists = [];
            for (let item of data.artists){
                let artist = {};
                try {
                    artist = await this.getArtistPromise(item.id);
                    artists.push(artist);
                }catch (err) {
                    callback(err);
                }
            }
            track["artists"] = artists;
            callback(null, track);
        })
        .catch(err => {
            callback(err);
        })
    }
    searchTracks(query, callback) {
    // web-api/reference/search
        const URL = `https://api.spotify.com/v1/search?q=${query}&type=track&offset=0&limit=1`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            callback(err);
        })
        .then(async (response) => {
            let tracks = [];
            for (const item of response.data.tracks.items){
                let track = {};
                try {
                    track = await this.getTrackPromise(item.id);
                    tracks.push(track);
                }catch (err) {
                    callback(err);
                }
            }
            callback(null, tracks);
        })
        .catch(err => {
            callback(err);
        })
    }
    getArtist(artistId, callback) {
    // web-api/reference/get-an-artist
        const URL = `https://api.spotify.com/v1/artists/${artistId}`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            callback(err);
        })
        .then(async (response) => {
            let artist = {};
            let data = response.data;
            artist["artistId"] = artistId;
            artist["followers"] = data.followers.total;
            artist["genres"] = data.genres;
            artist["name"] = data.name;
            artist["imageUrl"] = data.images.url;
            artist["popularity"] = data.popularity;
            callback(null, artist);
        })
        .catch(err => {
            callback(err);
        })
    }
    getArtistTopTracks(artistId, market, callback) {
    // web-api/reference/get-an-artists-top-tracks
        const URL = `https://api.spotify.com/v1/artists/${artistId}/top-tracks?market=${market}`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            if (err.response.data.error.status === 400){
                throw new ApiError();
            }
            callback(err);
        })
        .then(async (response) => {
            let tracks = [];
            let datas = response.data.tracks;
            for (const data of datas) {
                let track = {};
                track["albumId"] = data.album.id;
                track["durationMs"] = data.duration_ms;
                track["name"] = data.name;
                track["previewUrl"] = data.preview_url;
                track["popularity"] = data.popularity;
                track["trackId"] = data.id;
                let artists = [];
                for (let item of data.artists){
                    let artist = {};
                    try {
                        artist = await this.getArtistPromise(item.id);
                        artists.push(artist);
                    }catch (err) {
                        callback(err);
                    }
                }
                track["artists"] = artists;
                tracks.push(track);
            }
            callback(null, tracks);
        })
        .catch(err => {
            callback(err);
        })
    }
    getPlaylist(playlistId, callback) {
    // web-api/reference/get-playlist
        const URL = `https://api.spotify.com/v1/playlists/${playlistId}`;
        axios
        .get(URL, {
            headers: {
                Authorization: `Bearer ${this.AccessToken}`
            }})
        .catch(err => {
            if (err.response.data.error.status === 404){
                throw new EntityNotFoundError();
            }
            callback(err);
        })
        .then(async (response) => {
            let playlist = {};
            let data = response.data;
            playlist["description"] = data.description;
            playlist["followers"] = data.followers.total;
            playlist["owner"] = {};
            playlist.owner.userId = data.owner.id;
            playlist["name"] = data.name;
            playlist["imageUrl"] = data.images.url;
            playlist["public"] = data.public;
            playlist["playlistId"] = playlistId;

            let tracks = [];
            let i=0;
            while (i<3 && data.tracks.items[i] != null){
                let item = data.tracks.items[i];
                let track = {};
                try {
                    track = await this.getTrackPromise(item.track.id);
                    tracks.push(track);
                }catch (err) {
                    callback(err);
                }
                i++;
            }
            playlist["tracks"] = tracks;
            callback(null, playlist);
        })
        .catch(err => {
            callback(err);
        })
    }
}
exports.SpotifyApi = SpotifyApi;