'use strict';

const http = require('http'),
    PORT = 8088;
const fs = require("node:fs");

let err_num = 0,
    req_num = 0;

function factorial(val) {
    let res = 1n;
    while (val>0) {
        res *= val;
        val --;
    }
    return res
}

function anagrams(s){
    let repeated = 1n;
    const counted = new Map();
    for (let letter of s){
        if (!counted.has(letter)){
            counted.set(letter,1n);
        } else {
            counted.set(letter,counted.get(letter)+1n)
        }
    }
    for (let num of counted.values()){
        repeated *= factorial(num);
    }
    let res = factorial(BigInt(s.length)) / repeated;
    return {
        "p": s,
        "total": `${res}`
    }
}

const server = http.createServer((req, res) => {
    req_num++;
    if (req.method === 'GET') {
        if (req.url === '/ping') {
            res.writeHead(204, {'Content-Type': 'text/plain'});
            res.end();
        } else if (req.url.split('?')[0] === '/anagram') {
            let p = req.url.split('?')[1];
            p = p.substring(2);
            if (/^[a-zA-Z]+$/.test(p)) {
                let ans = anagrams(p.toLowerCase());
                let responseBody = JSON.stringify(ans);
                res.writeHead(200, {'Content-Type': 'text/plain'});
                res.write(responseBody);
                res.end();
            } else {
                err_num++;
                res.writeHead(400, {'Content-Type': 'text/plain'});
                res.end();
            }
        } else if (req.url === '/secret') {
            let filePath = '/tmp/secret.key';
            if (fs.existsSync(filePath)) {
                res.writeHead(200, {'Content-Type': 'text/plain'});
                res.end(fs.readFileSync(filePath));
            } else {
                err_num++;
                res.writeHead(404, {'Content-Type': 'text/plain'});
                res.end();
            }
        } else if (req.url === '/status') {
            let time = new Date();
            let responseBody = JSON.stringify({
                "time": time.toISOString().split('.')[0] + "Z",
                "req": req_num,
                "err": err_num
            });
            res.writeHead(200, {'Content-Type': 'text/plain'});
            res.end(responseBody);
        } else {
            err_num++;
            res.writeHead(404, {'Content-Type': 'text/plain'});
            res.end();
        }
    }
});

server.listen(PORT);
console.log(`Server on port ${PORT}`);