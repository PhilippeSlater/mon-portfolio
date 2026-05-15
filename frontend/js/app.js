const API = window.location.hostname === 'localhost'
  ? 'http://localhost:3001'
  : 'https://mon-portfolio-wcgl.onrender.com';

// ── Utilitaires ────────────────────────────────────────────────────────────
async function fetchJSON(url) {
  const res = await fetch(url);
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}

function createTechBadge(tech) {
  const span = document.createElement('span');
  span.className = 'tech-badge';
  span.textContent = tech;
  return span;
}

// ── Projets ─────────────────────────────────────────────────────────────────
function renderProjects(projects) {
  const grid = document.getElementById('projects-grid');
  grid.innerHTML = '';

  if (!projects.length) {
    grid.innerHTML = '<p class="loading">Aucun projet dans cette catégorie.</p>';
    return;
  }

  projects.forEach(p => {
    const card = document.createElement('article');
    card.className = 'project-card';
    card.dataset.category = p.category;

    const techBadges = p.tech.map(t => createTechBadge(t).outerHTML).join('');

    const links = [];
    if (p.demo) links.push(`<a href="${p.demo}" target="_blank" rel="noopener" class="project-link project-link--demo">Demo →</a>`);
    if (p.github) links.push(`<a href="${p.github}" target="_blank" rel="noopener" class="project-link project-link--github">GitHub</a>`);

    card.innerHTML = `
      <div class="project-card__header">
        <span class="project-card__year">${p.year}</span>
      </div>
      <h3 class="project-card__title">${p.title}</h3>
      <p class="project-card__desc">${p.description}</p>
      <div class="project-card__tech">${techBadges}</div>
      ${links.length ? `<div class="project-card__links">${links.join('')}</div>` : ''}
    `;
    grid.appendChild(card);
  });
}

async function loadProjects() {
  try {
    const projects = await fetchJSON(`${API}/api/projects`);
    renderProjects(projects);
    document.getElementById('stat-projects').textContent = projects.length;
    setupFilters(projects);
  } catch {
    document.getElementById('projects-grid').innerHTML = '<p class="loading">Impossible de charger les projets.</p>';
  }
}

function setupFilters(allProjects) {
  document.querySelectorAll('.filter-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      const filter = btn.dataset.filter;
      const filtered = filter === 'all' ? allProjects : allProjects.filter(p => p.category === filter);
      renderProjects(filtered);
    });
  });
}

// ── Compétences ──────────────────────────────────────────────────────────────
const CATEGORY_LABELS = {
  language:    'Langages',
  backend:     'Backend',
  frontend:    'Frontend',
  database:    'Bases de données',
  devops:      'DevOps & Cloud',
  embedded:    'Systèmes embarqués',
  methodology: 'Méthodologies',
};

async function loadSkills() {
  try {
    const skills = await fetchJSON(`${API}/api/skills`);
    document.getElementById('stat-skills').textContent = skills.length;

    const grouped = skills.reduce((acc, s) => {
      (acc[s.category] = acc[s.category] || []).push(s);
      return acc;
    }, {});

    const container = document.getElementById('skills-container');
    container.innerHTML = '';

    Object.entries(grouped).forEach(([cat, items]) => {
      const group = document.createElement('div');
      group.className = 'skill-group';
      const badges = items.map(s =>
        `<span class="skill-badge skill-badge--${s.level}">${s.name}</span>`
      ).join('');
      group.innerHTML = `
        <p class="skill-group__title">${CATEGORY_LABELS[cat] || cat}</p>
        <div class="skill-group__badges">${badges}</div>
      `;
      container.appendChild(group);
    });
  } catch {
    document.getElementById('skills-container').innerHTML = '<p class="loading">Impossible de charger les compétences.</p>';
  }
}

// ── Init ─────────────────────────────────────────────────────────────────────
loadProjects();
loadSkills();
