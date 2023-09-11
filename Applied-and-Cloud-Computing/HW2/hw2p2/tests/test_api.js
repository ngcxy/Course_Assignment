'use strict';

const { assert, expect } = require('chai');
const DEFAULT_TIMEOUT_MS = 4e3;
const { Fixture } = require('./fixture_hw2p2');

const {
  random_string
} = require('../lib/util');

const { ApiError, EntityNotFoundError } = require('../error');

// Dummy values for testing purposes. Replace with actual values.

const ALBUM = {
  EXIST: '4aawyAB9vmqN3uQ7FjRGTy',
  NOT_EXIST: '1111111111111111111111',
  SEARCH_QUERY: {
    VALID: 'ALBUM_NAME',
    INVALID: '11112222333344445556666777788889999'
  }
};

const ARTIST = {
  EXIST: '0TnOYISbd1XYRBk9myaseg',
  NOT_EXIST: '1111111111111111111111',
  TOP_TRACKS_MARKET: {
    VALID: 'US',
    INVALID: 'XX'
  }
};

const PLAYLIST = {
  EXIST: '3cEYpjA9oz9GiPac4AsH4n',
  NOT_EXIST: '1111111111111111111111'
};

const TRACK = {
  EXIST: '11dFghVXANMlKmJXsNCbNl',
  NOT_EXIST: '1111111111111111111111',
  SEARCH_QUERY: {
    VALID: 'TRACK_NAME',
    INVALID: '11112222333344445556666777788889999'
  }
};

const CLIENT_ID = process.env.CLIENT_ID || '3fcb9b65b067401493d5a56ecba92e28';
const CLIENT_SECRET = process.env.CLIENT_SECRET || '48bd0178e4284fa7b3c8c3431d1b52c2';


describe('spotify api', function () {
  this.timeout(DEFAULT_TIMEOUT_MS);

  const fix = new Fixture();
  
  // used by uut to set access
  let ACCESS_TOKEN = null;

  before(async () => {
    ACCESS_TOKEN = await Fixture.getAccessToken(CLIENT_ID, CLIENT_SECRET);
  });
  
  beforeEach(() => fix.before());
  afterEach(() => fix.after());

  context('getAlbum', () => {
    it('valid album id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getAlbum(ALBUM.EXIST, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.a.model('album');

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('invalid album id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getAlbum(ALBUM.NOT_EXIST, (err, data) => {
        try {
          expect(err).to.be.an.instanceof(EntityNotFoundError);

          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });

  context('searchAlbums', () => {
    it('search valid album', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.searchAlbums(ALBUM.SEARCH_QUERY.VALID, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.an('array').and.not.be.empty;
          data.forEach(obj => {
            expect(obj).to.be.a.model('album');
          });

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('search invalid album', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.searchAlbums(ALBUM.SEARCH_QUERY.INVALID, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.an('array').and.be.empty;

          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });









  context('getTrack', () => {
    it('valid track id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getTrack(TRACK.EXIST, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.a.model('track');

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('invalid track id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getTrack(TRACK.NOT_EXIST, (err, data) => {
        try {
          expect(err).to.be.an.instanceof(EntityNotFoundError);

          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });

context('searchTracks', () => {
    it('search valid track', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.searchTracks(TRACK.SEARCH_QUERY.VALID, (err, data) => {
        try {
          console.log(data);
          expect(err).to.not.exist;
          expect(data).to.be.an('array').and.not.be.empty;
          data.forEach(obj => {
            expect(obj).to.be.a.model('track');
          });

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('search invalid track', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.searchTracks(TRACK.SEARCH_QUERY.INVALID, (err, data) => {
        try {
          // console.log(data);
          expect(err).to.not.exist;
          expect(data).to.be.an('array').and.be.empty;


          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });
  context('getArtist', () => {
    it('valid artist id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getArtist(ARTIST.EXIST, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.a.model('artist');

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('invalid artist id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getArtist(ARTIST.NOT_EXIST, (err, data) => {
        try {
          expect(err).to.be.an.instanceof(EntityNotFoundError);

          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });


  context('getArtistTopTracks', () => {
    it('valid artist id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getArtistTopTracks(ARTIST.EXIST, ARTIST.TOP_TRACKS_MARKET.VALID, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.an('array').and.not.be.empty;
          data.forEach(obj => {
            expect(obj).to.be.a.model('track');
          });

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('invalid artist id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getArtistTopTracks(ARTIST.NOT_EXIST, ARTIST.TOP_TRACKS_MARKET.VALID, (err, data) => {
        try {
          expect(err).to.be.an.instanceof(EntityNotFoundError);

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('invalid market id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getArtistTopTracks(ARTIST.EXIST, ARTIST.TOP_TRACKS_MARKET.INVALID, (err, data) => {
        try {
          expect(err).to.be.an.instanceof(ApiError);

          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });

  context('getPlaylist', () => {
    it('valid playlist id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getPlaylist(PLAYLIST.EXIST, (err, data) => {
        try {
          expect(err).to.not.exist;
          expect(data).to.be.a.model('playlist');

          done();
        } catch(err) {
          done(err);
        }
      });
    });

    it('invalid playlist id', (done) => {
      const uut = fix.uut(ACCESS_TOKEN);

      uut.getArtist(PLAYLIST.NOT_EXIST, (err, data) => {
        try {
          expect(err).to.be.an.instanceof(EntityNotFoundError);

          done();
        } catch(err) {
          done(err);
        }
      });
    });
  });
});
