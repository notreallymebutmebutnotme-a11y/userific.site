document.addEventListener("DOMContentLoaded", () => {
  const toolsContainer = document.getElementById("tools-container");

  fetch("tools.json")
    .then(response => response.json())
    .then(tools => {
      // Detect repo base path automatically
      const repoBase = window.location.pathname.split("/")[1]; 
      const basePath = "/" + repoBase + "/tools/";

      tools.forEach(tool => {
        const toolCard = document.createElement("div");
        toolCard.className = "tool-card";
        toolCard.innerHTML = `
          <h2>${tool.name}</h2>
          <p>${tool.description}</p>
          <a href="${basePath + tool.slug}/" class="btn">Open Tool</a>
        `;
        toolsContainer.appendChild(toolCard);
      });
    })
    .catch(error => console.error("Error loading tools:", error));
});
