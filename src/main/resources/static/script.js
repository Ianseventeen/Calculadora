document.addEventListener('DOMContentLoaded', () => {
    const expressionDisplay = document.getElementById('expression');
    const resultDisplay = document.getElementById('result');
    const keypad = document.querySelector('.keypad');

    // Elementos do Modal
    const btnInfo = document.getElementById('btn-info');
    const infoModal = document.getElementById('info-modal');
    const closeModal = document.getElementById('close-modal');

    let currentInput = '';

    // Controle do Modal
    btnInfo.addEventListener('click', () => infoModal.classList.add('show'));
    closeModal.addEventListener('click', () => infoModal.classList.remove('show'));
    window.addEventListener('click', (e) => {
        if (e.target === infoModal) infoModal.classList.remove('show');
    });

    // Eventos do Teclado da Calculadora
    keypad.addEventListener('click', (event) => {
        const button = event.target.closest('button');
        if (!button) return;

        const value = button.dataset.value;
        const action = button.dataset.action;

        if (value !== undefined) {
            appendValue(value);
        } else if (action !== undefined) {
            handleAction(action);
        } else if (button.id === 'btn-equals') {
            calcularBackend();
        }
    });

    function appendValue(val) {
        currentInput += val;
        updateDisplay();
    }

    function handleAction(action) {
        if (action === 'clear') {
            currentInput = '';
            resultDisplay.innerText = '0';
            expressionDisplay.innerText = '';
        } else if (action === 'backspace') {
            currentInput = currentInput.slice(0, -1);
            updateDisplay();
        } else if (action === 'toggle-sign') {
            if (currentInput.startsWith('-')) {
                currentInput = currentInput.substring(1);
            } else if (currentInput !== '') {
                currentInput = '-' + currentInput;
            }
            updateDisplay();
        }
    }

    function updateDisplay() {
        expressionDisplay.innerText = currentInput;
    }

    // Atalhos do Teclado Físico
    document.addEventListener('keydown', (event) => {
        if (infoModal.classList.contains('show')) {
            if (event.key === 'Escape') infoModal.classList.remove('show');
            return;
        }

        const key = event.key;
        if (!isNaN(key) || ['+', '-', '*', '/', '.'].includes(key)) {
            appendValue(key);
        } else if (key === 'Enter') {
            event.preventDefault();
            calcularBackend();
        } else if (key === 'Backspace') {
            handleAction('backspace');
        } else if (key === 'Escape') {
            handleAction('clear');
        }
    });

    // Requisição ao Spring Boot
    async function calcularBackend() {
        if (!currentInput.trim()) return;

        resultDisplay.innerText = '...';

        try {
            const response = await fetch('http://localhost:8080/calculations/calculate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ expression: currentInput })
            });

            if (!response.ok) {
                throw new Error(`Erro: ${response.status}`);
            }

            const resultado = await response.text();
            resultDisplay.innerText = resultado;

        } catch (error) {
            resultDisplay.innerText = 'Erro';
            console.error('Falha na requisição:', error);
        }
    }
});