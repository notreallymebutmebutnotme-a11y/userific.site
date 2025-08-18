// Theme toggle + tools loader
(function(){
  const root = document.documentElement;
  const saved = localStorage.getItem('theme');
  if(saved){ root.setAttribute('data-theme', saved); }
  else{
    // default light; if user prefers dark, start dark
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches){
      root.setAttribute('data-theme', 'dark');
    }
  }
  const toggle = document.getElementById('themeToggle');
  toggle?.addEventListener('click', () => {
    const next = root.getAttribute('data-theme') === 'dark' ? 'light' : 'dark';
    root.setAttribute('data-theme', next);
    localStorage.setItem('theme', next);
  });

  // Year
  const y = document.getElementById('year');
  if(y) y.textContent = new Date().getFullYear();

  // Load tools.json
  const grid = document.getElementById('toolsGrid');
  const empty = document.getElementById('emptyState');
  const search = document.getElementById('search');

  async function loadTools(){
    try{
      const res = await fetch('tools.json', { cache: 'no-cache' });
      if(!res.ok) throw new Error('Failed to load tools.json');
      const items = await res.json();
      render(items);
      search?.addEventListener('input', () => {
        const q = (search.value || '').toLowerCase();
        const filtered = items.filter(t => (t.name + ' ' + (t.description||'')).toLowerCase().includes(q));
        render(filtered);
      });
    }catch(e){
      console.error(e);
      grid.innerHTML = '<div class="empty">Add a tools.json file to show tools here.</div>';
    }
  }

  function render(list){
    grid.innerHTML = '';
    if(!list.length){ empty.classList.remove('hidden'); return; }
    empty.classList.add('hidden');
    for(const t of list){
      const card = document.createElement('a');
      card.className = 'card';
      card.href = t.url;
      card.innerHTML = `
        <div>
          <h3>${escapeHtml(t.name)}</h3>
          <p>${escapeHtml(t.description || '')}</p>
        </div>
        <div class="cta">Open</div>
      `;
      grid.appendChild(card);
    }
  }

  function escapeHtml(str){
    return (str+'').replace(/[&<>"']/g, s=>({ '&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#039;' }[s]));
  }

  loadTools();
})();