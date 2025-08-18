document.addEventListener("DOMContentLoaded", () => {
  const toolsContainer = document.getElementById("tools-container");

  fetch("tools.json")
    .then(response => response.json())
    .then(tools => {
      // detect current site base automatically
      let basePath = window.location.pathname;
      
      // if you are on GitHub Pages, basePath will include repo name
      // ensure it always ends with a "/"
      if (!basePath.endsWith("/")) {
        basePath = basePath.substring(0, basePath.lastIndexOf("/") + 1);
      }

      tools.forEach(tool => {
        const toolCard = document.createElement("div");
        toolCard.className = "tool-card";
        toolCard.innerHTML = `
          <h2>${tool.name}</h2>
          <p>${tool.description}</p>
          <a href="${basePath}tools/${tool.slug}/" class="btn">Open Tool</a>
        `;
        toolsContainer.appendChild(toolCard);
      });
    })
    .catch(error => console.error("Error loading tools:", error));
});
