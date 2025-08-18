document.addEventListener("DOMContentLoaded", async () => {
  const toolsContainer = document.getElementById("tools-container");

  // Always resolve paths relative to the current page (repo or custom domain)
  const base = new URL(".", document.baseURI); // e.g. https://.../userific.site/

  let tools = [];
  try {
    const res = await fetch(new URL("tools.json", base));
    tools = await res.json();
  } catch (e) {
    console.error("Failed to load tools.json", e);
    return;
  }

  tools.forEach(tool => {
    // Build the tool URL RELATIVE to the current page (no leading slash!)
    const toolUrl = new URL(`tools/${tool.slug}/`, base).href;

    // Render a card (anchor wrapper avoids nested link issues)
    const card = document.createElement("a");
    card.className = "tool-card";
    card.href = toolUrl;
    card.innerHTML = `
      <h2>${tool.name}</h2>
      <p>${tool.description}</p>
      <div class="btn">Open Tool</div>
    `;
    toolsContainer.appendChild(card);
  });
});
