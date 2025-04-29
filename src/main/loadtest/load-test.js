import http from 'k6/http';
import { check } from 'k6';
import { Trend } from 'k6/metrics';

// Define a custom metric to track response times
let responseTimeTrend = new Trend('response_time');

// This is the default function that k6 will run
export default function () {
    // Define the URL to test
    const url = 'https://app.hamropatro.com';  // Replace with your actual URL

    // Send a GET request to the URL
    const res = http.get(url);

    // Check if the response status is 200 OK
    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    // Record the response time in the custom metric
    responseTimeTrend.add(res.timings.duration);
}
