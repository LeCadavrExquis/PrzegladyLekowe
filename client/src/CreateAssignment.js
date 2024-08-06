import { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const CreateAssignment = () => {
    const navigate = useNavigate();
    const [templates, setTemplates] = useState([]);
    const [name, setName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [templateId, setTemplateId] = useState("");

    const assign = useCallback(async () => {
        console.log(`
            name : ${name}
            templateId: ${templateId}
            userEmail: ${userEmail}
            `);

        const response = await fetch("/assignment", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name,
                userEmail,
                templateId,
            }),
        });

        if (response.ok) {
            navigate("/app");
        } else {
            console.error(response);
        }
    }, [name, templateId, userEmail, navigate]);

    const cancel = () => {
        navigate("/app");
    };

    useEffect(() => {
        const getTemplates = async () => {
            try {
                const response = await fetch("/templates", {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });

                const data = await response.json();
                setTemplates(data);

                if (data.length > 0) {
                    setTemplateId(data[0].id);
                }
            } catch (error) {
                console.error("Error fetching templates list", error);
            }
        };

        getTemplates();
    }, []);

    return (
        <div className="auth-container">
            <div className="form-container">
                <div className="form-row">
                    <label htmlFor="userEmail" className="form-label">
                        Patient Email
                    </label>
                    <input
                        id="userEmail"
                        type="email"
                        value={userEmail}
                        className="form-input"
                        onChange={(e) => setUserEmail(e.target.value)}
                    />
                </div>

                <div className="form-row">
                    <label htmlFor="name" className="form-label">
                        Assignment name
                    </label>
                    <input
                        id="name"
                        type="text"
                        value={name}
                        className="form-input"
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>

                <div className="form-row">
                    <label htmlFor="templateChoice" className="form-label">
                        Form template
                    </label>
                    <select
                        id="templateChoice"
                        className="form-input"
                        value={templateId}
                        onChange={(e) => setTemplateId(e.target.value)}
                    >
                        {templates.map((template) => (
                            <option
                                className="form-input"
                                key={template.id}
                                value={template.id}
                            >
                                {template.name}
                            </option>
                        ))}
                    </select>
                </div>

                <button className="auth-button" onClick={assign}>
                    Assign
                </button>
                <button className="auth-button" onClick={cancel}>
                    Cancel
                </button>
            </div>
        </div>
    );
};

export { CreateAssignment };
