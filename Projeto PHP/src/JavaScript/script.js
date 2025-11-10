const form = document.getElementById('prompt-form');
const reportDisplay = document.getElementById('report-display');
const reportList = document.getElementById('report-list');

async function loadReports() {
    const response = await fetch('Back-end/get-reports.php');
    const reports = await response.json();
    reportList.innerHTML = '';
    if(reports.length === 0) {
        reportList.innerHTML = '<li>Nenhum Relatório Salvo.</li>';
        return;
    } else {
        reports.forEach(report => {
            const listItem = document.createElement('li');
            listItem.innerHTML = report.prompt;
            listItem.addEventListener('click', () => {
                const formattedHtml = marked.parse(report.response);
                reportDisplay.innerHTML = '';
                reportDisplay.innerHTML = formattedHtml;
            })
            reportList.appendChild(listItem);
        })
    }
}

window.addEventListener('DOMContentLoaded', loadReports);
form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(form);
    reportDisplay.innerHTML = '';
    form.reset();
    const response = await fetch('Back-end/gemini-api.php', {
        method: 'POST',
        body: formData
    });
    const reportText = await response.text();
    const formattedHtml = marked.parse(reportText);
    const tempDiv = document.createElement('div');
    tempDiv.innerHTML = formattedHtml;
    const textToType = tempDiv.textContent || '';

    let i = 0;
    function typeWriter() {
        if (i < textToType.length) {
            reportDisplay.innerHTML += textToType.charAt(i);
            i++
            setTimeout(typeWriter, 10);
        } else {
            reportDisplay.innerHTML = formattedHtml;
        }
    }
    typeWriter();
    loadReports();
});