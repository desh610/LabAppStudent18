<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Test Results</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">Add Test Results</h2>
    <form action="submit_test_results.php" method="POST">
        <div class="mb-3">
            <label for="testType" class="form-label">Test Type</label>
            <select class="form-select" id="testType" name="testType" required>
                <option value="">Select Test Type</option>
                <option value="1">Test Type 1</option>
                <option value="2">Test Type 2</option>
            </select>
        </div>
        <div id="testTypeFields" class="mb-3">
            <!-- Fields for test results will be dynamically added here based on selected test type -->
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script>
    document.getElementById('testType').addEventListener('change', function() {
        var testType = this.value;
        var testTypeFields = document.getElementById('testTypeFields');

        // Clear existing fields
        testTypeFields.innerHTML = '';

        // Add fields based on selected test type
        if (testType === '1') {
            // Add 5 rows for test type 1
            for (var i = 1; i <= 5; i++) {
                testTypeFields.innerHTML += `
                    <div class="mb-3">
                        <label for="testType1Field${i}" class="form-label">Test Type 1 Field ${i}</label>
                        <input type="text" class="form-control" id="testType1Field${i}" name="testType1Field${i}" required>
                    </div>
                `;
            }
        } else if (testType === '2') {
            // Add 7 rows for test type 2
            for (var i = 1; i <= 7; i++) {
                testTypeFields.innerHTML += `
                    <div class="mb-3">
                        <label for="testType2Field${i}" class="form-label">Test Type 2 Field ${i}</label>
                        <input type="text" class="form-control" id="testType2Field${i}" name="testType2Field${i}" required>
                    </div>
                `;
            }
        }
    });
</script>

</body>
</html>
