// Banner em Carrossel
const slides = document.querySelectorAll('.slide');
let currentSlide = 0;
let autoSlideInterval;

function showSlide(slideIndex) {
  slides[currentSlide].classList.remove('active');
  slides[slideIndex].classList.add('active');
  currentSlide = slideIndex;
}

function nextSlide() {
  const nextSlide = (currentSlide + 1) % slides.length;
  showSlide(nextSlide);
}

function prevSlide() {
  const prevSlide = (currentSlide - 1 + slides.length) % slides.length;
  showSlide(prevSlide);
}

function startAutoSlide() {
  autoSlideInterval = setInterval(nextSlide, 8000); //8 segundos
}

function stopAutoSlide() {
  clearInterval(autoSlideInterval);
}

document.querySelector('.prev').addEventListener('click', function () {
  prevSlide();
  stopAutoSlide();
  startAutoSlide();
});

document.querySelector('.next').addEventListener('click', function () {
  nextSlide();
  stopAutoSlide();
  startAutoSlide();
});

startAutoSlide();
showSlide(currentSlide);
